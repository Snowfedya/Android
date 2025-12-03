package com.example.restaurantapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResource: Int,
    val category: String,
    val rating: Float
) : Parcelable