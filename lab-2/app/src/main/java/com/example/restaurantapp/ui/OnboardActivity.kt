package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import com.example.restaurantapp.base.BaseActivity
import com.example.restaurantapp.databinding.ActivityOnboardBinding

class OnboardActivity : BaseActivity() {

    private lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
