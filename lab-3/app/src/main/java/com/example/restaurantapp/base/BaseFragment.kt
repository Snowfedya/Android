package com.example.restaurantapp.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected val logTag: String = "LifecycleTag"
    private val className: String by lazy { this::class.java.simpleName }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(logTag, "$className: onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "$className: onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "$className: onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, "$className: onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "$className: onStart()")
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(logTag, "$className: onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "$className: onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(logTag, "$className: onDetach()")
    }
}
