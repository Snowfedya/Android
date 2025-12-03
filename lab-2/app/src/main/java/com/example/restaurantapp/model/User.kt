package com.example.restaurantapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val age: Int = 0,
    val gender: String = "",
    val phone: String = ""
) : Parcelable