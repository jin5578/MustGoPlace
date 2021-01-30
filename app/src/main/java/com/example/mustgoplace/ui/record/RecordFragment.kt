package com.example.mustgoplace.ui.record

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.R
import com.example.mustgoplace.databinding.FragmentRecordBinding
import com.example.mustgoplace.model.EventObserver
import com.example.mustgoplace.util.assistedFragmentViewModel
import com.example.mustgoplace.util.showAlertDialog
import com.example.mustgoplace.util.showToastMessage
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider


class RecordFragment : DaggerFragment() {

    private lateinit var binding: FragmentRecordBinding

    @Inject
    lateinit var viewModelProvider: Provider<RecordViewModel>
    private val viewModel by assistedFragmentViewModel { viewModelProvider.get() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecordFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()

        viewModel.showToast.observe(viewLifecycleOwner, EventObserver {
            showToastMessage(it)
        })

        viewModel.showAlertDialog.observe(viewLifecycleOwner, EventObserver {
            showAlertDialog(resources.getString(R.string.alert_dialog_content))
        })

        viewModel.showDatePicker.observe(viewLifecycleOwner, EventObserver {
            showDatePickerAlert(it)
        })

        viewModel.navigateToHome.observe(viewLifecycleOwner, EventObserver {
            findNavController().popBackStack()
        })
    }

    private fun showDatePickerAlert(eventDate: Triple<Int, Int, Int>) {
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                viewModel.setDateValue(year, month, dayOfMonth)
            },
            eventDate.first,
            eventDate.second,
            eventDate.third
        ).show()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.checkContentIsNull()
                }
            })
    }

}