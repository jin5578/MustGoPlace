package com.example.mustgoplace.util

import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.R


fun Fragment.onBackPressedAppKill() {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                appKillProcess()
            }
        })
}

fun Fragment.onBackPressedPopBackStack() {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
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

fun Fragment.showAlertDialog(content: String) {
    val inflater = layoutInflater.inflate(R.layout.layout_alert_dialog, null)
    val dialog = AlertDialog.Builder(requireContext()).apply {
        setView(inflater)
        setCancelable(false)
    }

    val alertDialog = dialog.create()

    inflater.apply {
        findViewById<TextView>(R.id.content).text = content
        findViewById<ImageView>(R.id.cancel).setOnClickListener {
            alertDialog.dismiss()
        }
        findViewById<ImageView>(R.id.check).setOnClickListener {
            alertDialog.dismiss()
            findNavController().popBackStack()
        }
    }

    alertDialog.show()
}

fun Fragment.showToastMessage(message: String) {
    val toastView = layoutInflater.inflate(
        R.layout.layout_custom_toast,
        requireActivity().findViewById(R.id.constraintLayout)
    ).apply {
        findViewById<TextView>(R.id.textView).text = message
    }

    with(Toast(requireContext())) {
        setGravity(Gravity.BOTTOM, 0, 260)
        duration = Toast.LENGTH_SHORT
        view = toastView

        show()
    }
}