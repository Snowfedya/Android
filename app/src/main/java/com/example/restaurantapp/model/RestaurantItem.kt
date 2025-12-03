package com.example.restaurantapp.model

data class RestaurantItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResource: Int,
    val category: String,
    val rating: Float
)