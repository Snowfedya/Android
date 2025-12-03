package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSignUp.setOnClickListener {
            performSignUp()
        }

        binding.btnBackToSignIn.setOnClickListener {
            finish() // Go back to SignIn
        }
    }

    private fun performSignUp() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        
        val genderId = binding.rgGender.checkedRadioButtonId
        val gender = if (genderId != -1) "Selected" else ""

        if (validateInput(name, email, password, confirmPassword, age, phone, gender)) {
            showSuccess("Регистрация успешна!")
            
            // In Lab 1 (No startActivityForResult), we can just start SignInActivity again 
            // or finish() and expect user to type. 
            // Better UX: Start SignInActivity with data.
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("registered_email", email)
            // Clear top to avoid stack pile up
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        age: String,
        phone: String,
        gender: String
    ): Boolean {
        return when {
            name.isEmpty() -> {
                showError("Пожалуйста, введите имя")
                false
            }
            name.length < 2 -> {
                showError("Имя должно содержать минимум 2 символа")
                false
            }
            email.isEmpty() -> {
                showError("Пожалуйста, введите email")
                false
            }
            !email.contains("@") -> {
                showError("Пожалуйста, введите корректный email")
                false
            }
            password.isEmpty() -> {
                showError("Пожалуйста, введите пароль")
                false
            }
            password.length < 6 -> {
                showError("Пароль должен содержать минимум 6 символов")
                false
            }
            password != confirmPassword -> {
                showError("Пароли не совпадают")
                false
            }
            age.isEmpty() -> {
                showError("Пожалуйста, введите возраст")
                false
            }
            phone.isEmpty() -> {
                showError("Пожалуйста, введите телефон")
                false
            }
             gender.isEmpty() -> {
                showError("Пожалуйста, выберите пол")
                false
            }
            else -> true
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
