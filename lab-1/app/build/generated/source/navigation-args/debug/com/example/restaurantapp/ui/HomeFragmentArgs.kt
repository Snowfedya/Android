package com.example.restaurantapp.ui

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class HomeFragmentArgs(
  public val userEmail: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("userEmail", this.userEmail)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("userEmail", this.userEmail)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): HomeFragmentArgs {
      bundle.setClassLoader(HomeFragmentArgs::class.java.classLoader)
      val __userEmail : String?
      if (bundle.containsKey("userEmail")) {
        __userEmail = bundle.getString("userEmail")
        if (__userEmail == null) {
          throw IllegalArgumentException("Argument \"userEmail\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"userEmail\" is missing and does not have an android:defaultValue")
      }
      return HomeFragmentArgs(__userEmail)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): HomeFragmentArgs {
      val __userEmail : String?
      if (savedStateHandle.contains("userEmail")) {
        __userEmail = savedStateHandle["userEmail"]
        if (__userEmail == null) {
          throw IllegalArgumentException("Argument \"userEmail\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"userEmail\" is missing and does not have an android:defaultValue")
      }
      return HomeFragmentArgs(__userEmail)
    }
  }
}
