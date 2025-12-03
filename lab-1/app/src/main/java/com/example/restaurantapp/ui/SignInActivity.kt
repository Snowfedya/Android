package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    // Фиктивные данные для входа
    private val validEmail = "test@example.com"
    private val validPassword = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        
        // Check for data passed from SignUp (Lab 1 style: via Intent extra)
        val registeredEmail = intent.getStringExtra("registered_email")
        if (!registeredEmail.isNullOrEmpty()) {
             binding.etEmail.setText(registeredEmail)
             Toast.makeText(this, "Registration successful! Please sign in.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            performSignIn()
        }

        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performSignIn() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showError("Пожалуйста, введите email")
                false
            }
            password.isEmpty() -> {
                showError("Пожалуйста, введите пароль")
                false
            }
            email == validEmail && password == validPassword -> {
                true
            }
            // Allow any valid formatted email/pass for demo
            email.contains("@") && password.length >= 6 -> {
                true
            }
            else -> {
                showError("Неверный email или пароль")
                false
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
