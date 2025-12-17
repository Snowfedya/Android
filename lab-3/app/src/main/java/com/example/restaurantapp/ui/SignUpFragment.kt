package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseFragment

class SignUpFragment : BaseFragment() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etAge: EditText
    private lateinit var etPhone: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var btnSignUp: Button
    private lateinit var btnBackToSignIn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupClickListeners()
    }

    private fun initViews(view: View) {
        etName = view.findViewById(R.id.etName)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword)
        etAge = view.findViewById(R.id.etAge)
        etPhone = view.findViewById(R.id.etPhone)
        rgGender = view.findViewById(R.id.rgGender)
        btnSignUp = view.findViewById(R.id.btnSignUp)
        btnBackToSignIn = view.findViewById(R.id.btnBackToSignIn)
    }

    private fun setupClickListeners() {
        btnSignUp.setOnClickListener {
            performSignUp()
        }

        btnBackToSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
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
            
            // LAB 3: Use Fragment Result API to pass data back
            setFragmentResult("requestKey", bundleOf("email" to email))
            parentFragmentManager.popBackStack()
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
