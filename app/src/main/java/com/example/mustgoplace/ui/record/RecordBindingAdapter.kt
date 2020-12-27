package com.example.mustgoplace.ui.record

import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mustgoplace.R

@BindingAdapter("alignImage")
fun alignImage(imageView: ImageView, align: Int) {
    val image = when (align) {
        0 -> R.drawable.ic_baseline_format_align_left_24
        1 -> R.drawable.ic_baseline_format_align_center_24
        else -> R.drawable.ic_baseline_format_align_right_24
    }

    Glide.with(imageView)
        .load(image)
        .into(imageView)
}

@BindingAdapter("alignText")
fun alignText(editText: EditText, align: Int) {
    val textAlign = when (align) {
        0 -> Gravity.LEFT
        1 -> Gravity.CENTER
        else -> Gravity.RIGHT
    }

    editText.gravity = textAlign
}