package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapp.R
import com.example.restaurantapp.adapter.RestaurantAdapter
import com.example.restaurantapp.base.BaseFragment
import com.example.restaurantapp.database.AppDatabase
import com.example.restaurantapp.databinding.FragmentHomeBinding
import com.example.restaurantapp.repository.RestaurantRepository
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var restaurantAdapter: RestaurantAdapter
    
    // Lab 7: Repository instance
    private lateinit var repository: RestaurantRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize Repository
        val database = AppDatabase.getInstance(requireContext())
        repository = RestaurantRepository(database.restaurantDao())

        setupRecyclerView()
        setupWelcomeMessage()
        setupSwipeRefresh()
        
        // Start observing data
        observeData()
        
        // Cold Start: Check if we need to fetch initial data
        checkInitialLoad()
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter()
        
        binding.recyclerView.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        restaurantAdapter.setOnItemClickListener { item ->
            Toast.makeText(
                requireContext(),
                "Выбрано: ${item.name} - ₽${item.price}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupWelcomeMessage() {
        val userEmail = arguments?.getString("email")
        if (!userEmail.isNullOrEmpty()) {
            binding.tvWelcome.text = "Добро пожаловать, $userEmail!"
        }
        
        binding.tvWelcome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }
    }

    private fun observeData() {
        // Lab 7: Observe Flow from Room
        lifecycleScope.launch {
            repository.restaurants.collect { items ->
                restaurantAdapter.submitList(items)
                
                // If list is empty and we are not refreshing, show empty state or loading?
                // For now just hide progress bar if it was shown manually (though SwipeRefresh handles it)
            }
        }
    }

    private fun checkInitialLoad() {
        lifecycleScope.launch {
            if (repository.isEmpty()) {
                // DB is empty, fetch from network
                binding.swipeRefreshLayout.isRefreshing = true
                refreshData()
            }
        }
    }

    private fun refreshData() {
        lifecycleScope.launch {
            try {
                repository.refreshRestaurants()
                // Success is handled by Flow observation
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error updating: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
