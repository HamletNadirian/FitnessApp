package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myProfileLayout = view.findViewById<View>(R.id.my_profile_layout)
        myProfileLayout.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_profile)
        }
        val reminderTrainingLayout = view.findViewById<View>(R.id.my_training_reminder_layout)
        reminderTrainingLayout.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_reminderFragment)
        }

        view.findViewById<View>(R.id.my_drinking_reminder_layout).setOnClickListener {
           showDrinkingReminderDialog()
           // showHeightDialog()
        }

    }
    private fun showDrinkingReminderDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_reminder_drinking, null)

        val hourPicker = dialogView.findViewById<NumberPicker>(R.id.hourPicker)
        hourPicker.minValue = 1
        hourPicker.maxValue = 12
        hourPicker.value = 3

        val literPicker = dialogView.findViewById<NumberPicker>(R.id.literPicker)
        literPicker.minValue = 1
        literPicker.maxValue = 12
        literPicker.value = 2

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()
        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val selectedHour = hourPicker.value
            val selectedLiter = literPicker.value
            // Обработка выбранных значений
            dialog.dismiss()
        }
        dialog.show()
    }
}

