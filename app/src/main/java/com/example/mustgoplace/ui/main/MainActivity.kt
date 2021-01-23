package com.example.mustgoplace.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.mustgoplace.R
import com.example.mustgoplace.databinding.ActivityMainBinding
import com.example.mustgoplace.ui.base.BaseActivity
import com.example.mustgoplace.util.assistedActivityViewModels
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Provider


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel>
    private val viewModel by assistedActivityViewModels { viewModelProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
    }

}