package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseActivity
import com.example.restaurantapp.databinding.ActivityOnboardBinding

class OnboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        findViewById<Button>(R.id.btnGetStarted).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
