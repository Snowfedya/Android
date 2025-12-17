package com.example.restaurantapp.network

import com.example.restaurantapp.model.RestaurantItem
import retrofit2.http.GET

interface RestaurantApiService {
    @GET("restaurants") // Placeholder endpoint
    suspend fun getRestaurants(): List<RestaurantItem>
}
