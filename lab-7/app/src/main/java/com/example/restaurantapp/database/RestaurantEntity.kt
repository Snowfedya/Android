package com.example.restaurantapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.model.RestaurantItem

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResource: Int,
    val category: String,
    val rating: Float
) {
    // Mapper function to convert Entity to Domain model
    fun toDomainModel(): RestaurantItem {
        return RestaurantItem(
            id = id,
            name = name,
            description = description,
            price = price,
            imageResource = imageResource,
            category = category,
            rating = rating
        )
    }
}

// Extension function to convert Domain model to Entity
fun RestaurantItem.toEntity(): RestaurantEntity {
    return RestaurantEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        imageResource = imageResource,
        category = category,
        rating = rating
    )
}
