package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseFragment
import com.example.restaurantapp.databinding.FragmentSignInBinding
import com.example.restaurantapp.model.User

class SignInFragment : BaseFragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    // Фиктивные данные для входа
    private val validEmail = "test@example.com"
    private val validPassword = "123456"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        
        // LAB 3: Receive result from SignUpFragment using modern Fragment Result API
        setFragmentResultListener("request_key_signup") { requestKey, bundle ->
            val user = bundle.getParcelable<User>("user_data")
            if (user != null) {
                binding.etEmail.setText(user.email)
                Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            performSignIn()
        }

        binding.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun performSignIn() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            val bundle = Bundle().apply {
                putString("email", email)
            }
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment, bundle)
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

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
