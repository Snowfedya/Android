package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.restaurantapp.base.BaseFragment
import com.example.restaurantapp.databinding.FragmentSignUpBinding
import com.example.restaurantapp.model.User

class SignUpFragment : BaseFragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSignUp.setOnClickListener {
            performSignUp()
        }

        binding.btnBackToSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
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
            val user = User(
                name = name,
                email = email,
                password = password,
                age = age.toIntOrNull() ?: 0,
                gender = gender,
                phone = phone
            )

            // LAB 3: Return result using setFragmentResult
            setFragmentResult("request_key_signup", bundleOf("user_data" to user))
            
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
