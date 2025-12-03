package com.example.restaurantapp.model

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val age: Int = 0,
    val gender: String = "",
    val phone: String = ""
)