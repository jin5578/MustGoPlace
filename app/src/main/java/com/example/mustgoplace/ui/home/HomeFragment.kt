package com.example.mustgoplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.databinding.FragmentHomeBinding
import com.example.mustgoplace.model.EventObserver
import com.example.mustgoplace.util.appKillProcess
import com.example.mustgoplace.util.assistedFragmentViewModel
import com.example.mustgoplace.util.showToastMessage
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider


class HomeFragment : DaggerFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }


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

        onBackPressed()
        setInitRecycler()

        viewModel.showToast.observe(viewLifecycleOwner, EventObserver {
            showToastMessage(it)
        })

        viewModel.navigateToRecord.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionToRecordFromHome())
        })

        viewModel.navigateToSetting.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionToSettingFromHome())
        })

        viewModel.appkillProcess.observe(viewLifecycleOwner, EventObserver {
            appKillProcess()
        })
    }

    private fun setInitRecycler() {
        binding.rvRecordList.apply {
            adapter = PlaceListAdapter(this@HomeFragment, viewModel)
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.backPressed()
                }
            })
    }

}