package com.example.restaurantapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.restaurantapp.base.BaseActivity
import com.example.restaurantapp.databinding.ActivitySignInBinding
import com.example.restaurantapp.model.User

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding

    // Фиктивные данные для входа
    private val validEmail = "test@example.com"
    private val validPassword = "123456"

    companion object {
        const val REQUEST_CODE_SIGN_UP = 1001
        const val KEY_USER_DATA = "user_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            performSignIn()
        }

        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // LAB 2: Используем startActivityForResult (Legacy)
            startActivityForResult(intent, REQUEST_CODE_SIGN_UP)
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
            email.contains("@") && password.length >= 6 -> {
                true
            }
            else -> {
                showError("Неверный email или пароль")
                false
            }
        }
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