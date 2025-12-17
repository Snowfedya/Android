package com.example.restaurantapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.restaurantapp.base.BaseFragment
import com.example.restaurantapp.data.BackupManager
import com.example.restaurantapp.data.SettingsDataStore
import com.example.restaurantapp.data.SettingsPreferences
import com.example.restaurantapp.databinding.FragmentSettingsBinding
import com.example.restaurantapp.network.NetworkModule
import kotlinx.coroutines.launch

class SettingsFragment : BaseFragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingsDataStore: SettingsDataStore
    private lateinit var settingsPrefs: SettingsPreferences
    private lateinit var backupManager: BackupManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (allGranted) {
            createBackup()
        } else {
            Toast.makeText(requireContext(), "Permissions required for backup", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        settingsDataStore = SettingsDataStore(requireContext())
        settingsPrefs = SettingsPreferences(requireContext())
        backupManager = BackupManager(requireContext())

        setupUI()
        observeSettings()
        refreshBackupInfo()
    }

    private fun setupUI() {
        // Load simple prefs
        binding.etEmail.setText(settingsPrefs.email)
        binding.etNickname.setText(settingsPrefs.nickname)
        binding.etBackupFilename.setText(settingsPrefs.backupFilename)

        // Save on focus lost or text change could be implemented here
        // For simplicity, we save when leaving or clicking buttons, 
        // but let's add a save button or just save on pause?
        // Let's save on Pause for simple prefs
        
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingsDataStore.saveTheme(isChecked)
            }
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingsDataStore.saveNotifications(isChecked)
            }
        }

        binding.btnCreateBackup.setOnClickListener {
            saveSimplePrefs() // Ensure filename is saved
            checkPermissionsAndCreateBackup()
        }

        binding.btnDeleteBackup.setOnClickListener {
            deleteBackup()
        }

        binding.btnRestoreBackup.setOnClickListener {
            restoreBackup()
        }
    }

    private fun observeSettings() {
        lifecycleScope.launch {
            settingsDataStore.themeFlow.collect { isDark ->
                binding.switchTheme.isChecked = isDark
                // In a real app, we might apply theme here or in MainActivity
            }
        }

        lifecycleScope.launch {
            settingsDataStore.notificationsFlow.collect { isEnabled ->
                binding.switchNotifications.isChecked = isEnabled
            }
        }
    }

    private fun saveSimplePrefs() {
        settingsPrefs.email = binding.etEmail.text.toString()
        settingsPrefs.nickname = binding.etNickname.text.toString()
        settingsPrefs.backupFilename = binding.etBackupFilename.text.toString()
    }

    override fun onPause() {
        super.onPause()
        saveSimplePrefs()
    }

    private fun checkPermissionsAndCreateBackup() {
        val permissions = mutableListOf<String>()
        
        // READ_EXTERNAL_STORAGE is needed for older Android versions to check existence
        // WRITE_EXTERNAL_STORAGE is needed for Android <= 9
        // For Android 10+ (Q), we use scoped storage (MediaStore or specific directories)
        // But here we are using File API on public directory which requires permissions on older OS
        
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) 
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
        
        // For Android 13+ we might need READ_MEDIA_IMAGES etc, but for documents usually no specific permission if we own the file
        // However, to read other files or re-write, we might need permissions.
        // Let's keep it simple for the lab context.
        
        if (permissions.isNotEmpty()) {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        } else {
            createBackup()
        }
    }

    private fun createBackup() {
        lifecycleScope.launch {
            binding.btnCreateBackup.isEnabled = false
            
            // Fetch data (simulated from NetworkModule or Repository)
            // In a real app, we'd get this from a Repository
            try {
                val items = NetworkModule.apiService.getRestaurants()
                
                val result = backupManager.createOrUpdateBackup(items)
                
                if (result.isSuccess) {
                    Toast.makeText(requireContext(), "Backup created successfully", Toast.LENGTH_SHORT).show()
                    refreshBackupInfo()
                } else {
                    Toast.makeText(requireContext(), "Failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error fetching data: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.btnCreateBackup.isEnabled = true
            }
        }
    }

    private fun deleteBackup() {
        lifecycleScope.launch {
            binding.btnDeleteBackup.isEnabled = false
            
            val result = backupManager.deleteBackup()
            
            if (result.isSuccess) {
                Toast.makeText(requireContext(), "Backup deleted (copy saved internally)", Toast.LENGTH_SHORT).show()
                refreshBackupInfo()
            } else {
                Toast.makeText(requireContext(), "Failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
            }
            
            binding.btnDeleteBackup.isEnabled = true
        }
    }

    private fun restoreBackup() {
        lifecycleScope.launch {
            binding.btnRestoreBackup.isEnabled = false
            
            val result = backupManager.restoreBackup()
            
            if (result.isSuccess) {
                Toast.makeText(requireContext(), "Backup restored successfully", Toast.LENGTH_SHORT).show()
                refreshBackupInfo()
            } else {
                Toast.makeText(requireContext(), "Failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
            }
            
            binding.btnRestoreBackup.isEnabled = true
        }
    }

    private fun refreshBackupInfo() {
        lifecycleScope.launch {
            val info = backupManager.getExternalBackupInfo()
            val hasInternal = backupManager.hasInternalBackup()

            if (info.exists) {
                binding.tvBackupStatus.text = "Backup Available"
                binding.tvBackupPath.text = info.path
                binding.tvBackupDetails.text = "Size: ${info.size} bytes | Modified: ${info.lastModified}"
                binding.btnDeleteBackup.isEnabled = true
                binding.cardBackupInfo.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_primary80)
                )
            } else {
                binding.tvBackupStatus.text = "No External Backup"
                binding.tvBackupPath.text = "Path: ..."
                binding.tvBackupDetails.text = ""
                binding.btnDeleteBackup.isEnabled = false
                binding.cardBackupInfo.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_neutral90)
                )
            }

            binding.btnRestoreBackup.isEnabled = hasInternal
            if (hasInternal) {
                binding.btnRestoreBackup.text = "Restore from Internal Backup"
            } else {
                binding.btnRestoreBackup.text = "No Internal Backup Available"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
