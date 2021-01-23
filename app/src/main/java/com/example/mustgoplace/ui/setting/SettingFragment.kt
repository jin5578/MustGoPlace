package com.example.mustgoplace.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.databinding.FragmentSettingBinding
import com.example.mustgoplace.model.EventObserver
import com.example.mustgoplace.util.assistedFragmentViewModel
import com.example.mustgoplace.util.onBackPressedPopBackStack
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class SettingFragment : DaggerFragment() {

    private lateinit var binding: FragmentSettingBinding

    @Inject
    lateinit var viewModelProvider: Provider<SettingViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false).apply {
            viewModel = this@SettingFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressedPopBackStack()

        viewModel.navigateToHome.observe(viewLifecycleOwner, EventObserver {
            findNavController().popBackStack()
        })
    }

}