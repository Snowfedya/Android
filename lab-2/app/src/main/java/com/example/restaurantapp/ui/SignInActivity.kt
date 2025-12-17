package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseActivity
import com.example.restaurantapp.model.User

class SignInActivity : BaseActivity() {

    // Фиктивные данные для входа
    private val validEmail = "test@example.com"
    private val validPassword = "123456"

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button

    companion object {
        const val KEY_USER_DATA = "user_data"
        const val REQUEST_CODE_SIGN_UP = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnSignUp = findViewById(R.id.btnGoToSignUp) // Corrected ID from instruction
    }

    private fun setupClickListeners() {
        btnSignIn.setOnClickListener {
            performSignIn()
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // LAB 2: Используем startActivityForResult для получения данных обратно
            startActivityForResult(intent, REQUEST_CODE_SIGN_UP)
        }
    }

    // LAB 2: Обработка результата из SignUpActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == RESULT_OK) {
            // Получаем объект User (Parcelable)
            val user = data?.getParcelableExtra<User>(KEY_USER_DATA)
            if (user != null) {
                etEmail.setText(user.email)
                Toast.makeText(this, "Registration successful for ${user.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performSignIn() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            if (email == validEmail && password == validPassword) {
                navigateToHome()
            } else {
                Toast.makeText(this, "Demo: Signing in...", Toast.LENGTH_SHORT).show()
                navigateToHome()
            }
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
    // LAB 2: Получаем результат из SignUpActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == RESULT_OK) {
            // Получаем Parcelable объект
            val user = data?.getParcelableExtra<User>(KEY_USER_DATA)
            if (user != null) {
                binding.etEmail.setText(user.email)
                showError("Регистрация успешна! Email заполнен.")
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}