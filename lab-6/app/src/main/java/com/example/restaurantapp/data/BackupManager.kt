package com.example.restaurantapp.data

import android.content.Context
import android.os.Environment
import android.util.Log
import com.example.restaurantapp.model.RestaurantItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Lab 6: Manages backup operations to External and Internal storage.
 * 
 * Features:
 * - Create/Update backup to External Storage (/Documents)
 * - Delete backup (with hidden copy to Internal Storage)
 * - Restore backup from Internal to External Storage
 * - Get backup file info (name, path, size, date)
 */
class BackupManager(private val context: Context) {

    companion object {
        private const val TAG = "BackupManager"
        private const val INTERNAL_BACKUP_FILENAME = "backup_internal.txt"
    }

    private val settingsPrefs = SettingsPreferences(context)

    /**
     * Result class for backup operations
     */
    data class BackupInfo(
        val exists: Boolean,
        val filename: String = "",
        val path: String = "",
        val size: Long = 0,
        val lastModified: String = ""
    )

    /**
     * Get the external backup file in /Documents directory
     */
    private fun getExternalBackupFile(): File? {
        val documentsDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS
        )
        if (!documentsDir.exists()) {
            documentsDir.mkdirs()
        }
        return File(documentsDir, settingsPrefs.backupFilename)
    }

    /**
     * Get the internal backup file (hidden copy)
     */
    private fun getInternalBackupFile(): File {
        return File(context.filesDir, INTERNAL_BACKUP_FILENAME)
    }

    /**
     * Create or update backup file in External Storage
     */
    suspend fun createOrUpdateBackup(items: List<RestaurantItem>): Result<String> = 
        withContext(Dispatchers.IO) {
            try {
                val backupFile = getExternalBackupFile()
                    ?: return@withContext Result.failure(Exception("Cannot access external storage"))

                // Convert items to text format
                val content = buildString {
                    appendLine("=== Restaurant Backup ===")
                    appendLine("Created: ${getCurrentDateTime()}")
                    appendLine("Total items: ${items.size}")
                    appendLine("========================")
                    appendLine()
                    
                    items.forEachIndexed { index, item ->
                        appendLine("--- Item ${index + 1} ---")
                        appendLine("ID: ${item.id}")
                        appendLine("Name: ${item.name}")
                        appendLine("Description: ${item.description}")
                        appendLine("Price: ${item.price}")
                        appendLine("Category: ${item.category}")
                        appendLine("Rating: ${item.rating}")
                        appendLine()
                    }
                }

                backupFile.writeText(content)
                Log.d(TAG, "Backup created: ${backupFile.absolutePath}")
                
                Result.success(backupFile.absolutePath)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to create backup", e)
                Result.failure(e)
            }
        }

    /**
     * Delete external backup, but first copy to internal storage (hidden backup)
     */
    suspend fun deleteBackup(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val externalFile = getExternalBackupFile()
            
            if (externalFile == null || !externalFile.exists()) {
                return@withContext Result.failure(Exception("No backup file found"))
            }

            // Copy to internal storage first (hidden backup)
            val internalFile = getInternalBackupFile()
            externalFile.copyTo(internalFile, overwrite = true)
            settingsPrefs.hasInternalBackup = true
            Log.d(TAG, "Hidden copy saved to: ${internalFile.absolutePath}")

            // Now delete the external file
            val deleted = externalFile.delete()
            if (deleted) {
                Log.d(TAG, "External backup deleted")
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete external backup"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete backup", e)
            Result.failure(e)
        }
    }

    /**
     * Restore backup from internal storage to external storage
     */
    suspend fun restoreBackup(): Result<String> = withContext(Dispatchers.IO) {
        try {
            val internalFile = getInternalBackupFile()
            
            if (!internalFile.exists()) {
                return@withContext Result.failure(Exception("No backup to restore"))
            }

            val externalFile = getExternalBackupFile()
                ?: return@withContext Result.failure(Exception("Cannot access external storage"))

            // Copy from internal to external
            internalFile.copyTo(externalFile, overwrite = true)
            Log.d(TAG, "Backup restored to: ${externalFile.absolutePath}")

            Result.success(externalFile.absolutePath)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to restore backup", e)
            Result.failure(e)
        }
    }

    /**
     * Get information about the external backup file
     */
    suspend fun getExternalBackupInfo(): BackupInfo = withContext(Dispatchers.IO) {
        val file = getExternalBackupFile()
        
        if (file == null || !file.exists()) {
            return@withContext BackupInfo(exists = false)
        }

        BackupInfo(
            exists = true,
            filename = file.name,
            path = file.absolutePath,
            size = file.length(),
            lastModified = formatDate(file.lastModified())
        )
    }

    /**
     * Check if there's a hidden internal backup available for restore
     */
    fun hasInternalBackup(): Boolean {
        return settingsPrefs.hasInternalBackup && getInternalBackupFile().exists()
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
