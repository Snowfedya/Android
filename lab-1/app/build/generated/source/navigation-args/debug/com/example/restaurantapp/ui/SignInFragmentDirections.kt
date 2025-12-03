package com.example.restaurantapp.ui

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.restaurantapp.R
import kotlin.Int
import kotlin.String

public class SignInFragmentDirections private constructor() {
  private data class ActionSignInFragmentToHomeFragment(
    public val userEmail: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_signInFragment_to_homeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("userEmail", this.userEmail)
        return result
      }
  }

  public companion object {
    public fun actionSignInFragmentToSignUpFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_signInFragment_to_signUpFragment)

    public fun actionSignInFragmentToHomeFragment(userEmail: String): NavDirections =
        ActionSignInFragmentToHomeFragment(userEmail)
  }
}
