package com.example.mustgoplace.ui.revise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mustgoplace.databinding.FragmentReviseBinding
import com.example.mustgoplace.util.assistedFragmentViewModel
import com.example.mustgoplace.util.onBackPressedPopBackStack
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class ReviseFragment : DaggerFragment() {

    private lateinit var binding: FragmentReviseBinding

    @Inject
    lateinit var viewModelProvider: Provider<ReviseViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviseBinding.inflate(inflater, container, false).apply {
            viewModel = this@ReviseFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressedPopBackStack()
    }

}