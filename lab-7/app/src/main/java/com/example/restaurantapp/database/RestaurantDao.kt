package com.example.restaurantapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants ORDER BY id ASC")
    fun observeAll(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: List<RestaurantEntity>)

    @Query("DELETE FROM restaurants")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM restaurants")
    suspend fun count(): Int
}
