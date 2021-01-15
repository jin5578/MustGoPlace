package com.example.mustgoplace.ui.record

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mustgoplace.R
import com.example.mustgoplace.databinding.FragmentRecordBinding
import com.example.mustgoplace.model.EventObserver
import com.example.mustgoplace.util.showAlertDialog


class RecordFragment : Fragment() {

    private lateinit var binding: FragmentRecordBinding

    private val viewModel by viewModels<RecordViewModel>()

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