package com.example.mustgoplace.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


fun Fragment.onBackPressedAppKill() {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                appKillProcess()
            }
        })
}

fun Fragment.appKillProcess() {
    requireActivity().apply {
        moveTaskToBack(true)
        finishAffinity()
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}