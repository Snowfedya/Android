package com.example.restaurantapp.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.restaurantapp.R

public class OnboardFragmentDirections private constructor() {
  public companion object {
    public fun actionOnboardFragmentToSignInFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_onboardFragment_to_signInFragment)
  }
}
