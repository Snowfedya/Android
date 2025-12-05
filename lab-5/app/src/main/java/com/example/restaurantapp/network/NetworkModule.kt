package com.example.restaurantapp.network

import retrofit2.Retrofit
import retrofit2.converter-gson.GsonConverterFactory

object NetworkModule {
    // Using a mock API service or localhost. 
    // For demo purposes, we can use a placeholder or a service like mocky.io
    private const val BASE_URL = "https://run.mocky.io/v3/" 

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: RestaurantApiService by lazy {
        retrofit.create(RestaurantApiService::class.java)
    }
}
