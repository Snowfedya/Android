package com.example.restaurantapp.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected val logTag: String = "LifecycleTag"
    private val className: String by lazy { this::class.java.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "$className: onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "$className: onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(logTag, "$className: onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "$className: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(logTag, "$className: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(logTag, "$className: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "$className: onDestroy()")
    }
}
