package com.example.restaurantapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import com.example.restaurantapp.R
import com.example.restaurantapp.base.BaseFragment

class OnboardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    private fun setupClickListeners(view: View) {
        view.findViewById<Button>(R.id.btnGetStarted).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, SignInFragment())
                addToBackStack(null)
            }
        }
    }
}
