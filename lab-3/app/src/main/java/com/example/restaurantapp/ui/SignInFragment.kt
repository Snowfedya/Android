```kotlin
package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseFragment

class SignInFragment : BaseFragment() {

    private val validEmail = "test@example.com"
    private val validPassword = "123456"

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnGoToSignUp: Button // Changed from btnSignUp to match original layout ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupClickListeners()
        setupFragmentResultListener()
    }

    private fun initViews(view: View) {
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        btnSignIn = view.findViewById(R.id.btnSignIn)
        btnGoToSignUp = view.findViewById(R.id.btnGoToSignUp) // Changed from btnSignUp to match original layout ID
    }

    private fun setupClickListeners() {
        btnSignIn.setOnClickListener {
            performSignIn()
        }

        btnGoToSignUp.setOnClickListener { // Changed from btnSignUp to match original layout ID
            parentFragmentManager.commit {
                replace(R.id.fragment_container, SignUpFragment())
                addToBackStack(null)
            }
        }
    }

    private fun setupFragmentResultListener() {
        // LAB 3: Receive result from SignUpFragment using modern Fragment Result API
        setFragmentResultListener("request_key_signup") { requestKey, bundle -> // Kept original request key
            val email = bundle.getString("user_email") // Changed from "user_data" to "user_email" to match new logic
            if (email != null) {
                etEmail.setText(email)
                Toast.makeText(requireContext(), "Registration successful for $email", Toast.LENGTH_SHORT).show() // Updated Toast message
            }
        }
    }

    private fun performSignIn() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            if (email == validEmail && password == validPassword) {
                navigateToHome(email)
            } else {
                // Original code had a direct sign-in if validEmail/Password matched.
                // The new instruction's performSignIn has a "Demo: Signing in..." toast
                // and then navigates to home regardless if it's not the validEmail/Password.
                // I'll follow the new instruction's logic for this part.
                Toast.makeText(requireContext(), "Demo: Signing in...", Toast.LENGTH_SHORT).show()
                navigateToHome(email)
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
            // Removed the specific validEmail/validPassword check from here as per new instruction's validateInput
            // The check is now in performSignIn
            else -> true
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome(email: String) {
        val fragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putString("user_email", email) // Changed key to "user_email" for consistency
            }
        }
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}
```
