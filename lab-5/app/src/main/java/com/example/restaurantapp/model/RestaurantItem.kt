package com.example.restaurantapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class RestaurantItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("image_url") val imageUrl: String = "",
    val imageResource: Int = 0, // Local fallback
    @SerializedName("category") val category: String,
    @SerializedName("rating") val rating: Float
) : Parcelable