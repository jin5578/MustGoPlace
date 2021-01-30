package com.example.mustgoplace.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mustgoplace.databinding.FragmentSearchBinding
import com.example.mustgoplace.util.assistedFragmentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider


class SearchFragment : DaggerFragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModelProvider: Provider<SearchViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}