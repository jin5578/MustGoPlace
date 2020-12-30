package com.example.mustgoplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.databinding.FragmentHomeBinding
import com.example.mustgoplace.model.EventObserver

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToRecord.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionToRecordFromHome())
        })

        viewModel.navigateToSetting.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionToSettingFromHome())
        })

        viewModel.navigateToSetting.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionToSettingFromHome())
        })
    }

}