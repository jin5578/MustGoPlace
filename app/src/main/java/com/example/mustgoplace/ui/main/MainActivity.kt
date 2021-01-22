package com.example.mustgoplace.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.mustgoplace.R
import com.example.mustgoplace.databinding.ActivityMainBinding
import com.example.mustgoplace.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
    }

}