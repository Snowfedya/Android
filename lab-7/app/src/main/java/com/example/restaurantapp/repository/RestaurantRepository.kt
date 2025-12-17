package com.example.restaurantapp.repository

import android.util.Log
import com.example.restaurantapp.database.RestaurantDao
import com.example.restaurantapp.database.toEntity
import com.example.restaurantapp.model.RestaurantItem
import com.example.restaurantapp.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Repository that acts as a Single Source of Truth (SSOT).
 * Data is always observed from the Database (Room).
 * Network calls update the Database.
 */
class RestaurantRepository(private val dao: RestaurantDao) {

    private val TAG = "RestaurantRepository"

    // Observe data from Database and map to Domain model
    val restaurants: Flow<List<RestaurantItem>> = dao.observeAll().map { entities ->
        entities.map { it.toDomainModel() }
    }

    /**
     * Refresh data from Network and save to Database.
     * This is the "write" operation.
     */
    suspend fun refreshRestaurants() {
        withContext(Dispatchers.IO) {
            try {
                // 1. Fetch from API
                val items = NetworkModule.apiService.getRestaurants()
                
                // 2. Map to Entities
                val entities = items.map { it.toEntity() }
                
                // 3. Save to DB (Room will emit new data to 'restaurants' Flow)
                dao.insertAll(entities)
                
                Log.d(TAG, "Refreshed ${items.size} items from network")
            } catch (e: Exception) {
                Log.e(TAG, "Network refresh failed", e)
                // We don't throw here to allow UI to keep showing cached data
                // But in a real app, we might want to expose errors via a separate Flow
                throw e 
            }
        }
    }

    /**
     * Check if database is empty to trigger initial load
     */
    suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        dao.count() == 0
    }
}
