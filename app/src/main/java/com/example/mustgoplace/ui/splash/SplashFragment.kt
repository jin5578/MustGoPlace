package com.example.mustgoplace.ui.splash

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.databinding.FragmentSplashBinding
import com.example.mustgoplace.model.EventObserver
import com.example.mustgoplace.util.assistedFragmentViewModel
import com.example.mustgoplace.util.onBackPressedAppKill
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : DaggerFragment() {

    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var viewModelProvider: Provider<SplashViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false).apply {
            viewModel = this@SplashFragment.viewModel
            lifecycleOwner = this@SplashFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitView()

        viewModel.navigateToHome.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(SplashFragmentDirections.actionToHomeFromSplash())
        })
    }

    private fun setInitView() {
        onBackPressedAppKill()

        setNoStatusBar()
    }

    @Suppress("DEPRECATION")
    private fun setNoStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

}