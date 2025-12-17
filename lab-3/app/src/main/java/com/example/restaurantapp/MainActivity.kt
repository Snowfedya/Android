package com.example.restaurantapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.ui.OnboardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OnboardFragment())
                .commit()
        }
    }
}
