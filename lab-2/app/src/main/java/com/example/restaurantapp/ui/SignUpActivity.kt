package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseActivity
import com.example.restaurantapp.model.User

class SignUpActivity : BaseActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etAge: EditText
    private lateinit var etPhone: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var btnSignUp: Button
    private lateinit var btnBackToSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etAge = findViewById(R.id.etAge)
        etPhone = findViewById(R.id.etPhone)
        rgGender = findViewById(R.id.rgGender)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnBackToSignIn = findViewById(R.id.btnBackToSignIn)
    }

    private fun setupClickListeners() {
        btnSignUp.setOnClickListener {
            performSignUp()
        }

        btnBackToSignIn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun performSignUp() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val age = etAge.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        
        val genderId = rgGender.checkedRadioButtonId
        val gender = if (genderId != -1) "Selected" else ""

        if (validateInput(name, email, password, confirmPassword, age, phone, gender)) {
            
            // Создаем объект User (Parcelable)
            val user = User(
                name = name,
                email = email,
                password = password,
                age = age.toIntOrNull() ?: 0,
                gender = gender,
                phone = phone
            )

            // LAB 2: Возвращаем результат в SignInActivity
            val resultIntent = Intent().apply {
                putExtra(SignInActivity.KEY_USER_DATA, user)
            }
            setResult(RESULT_OK, resultIntent)
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
}