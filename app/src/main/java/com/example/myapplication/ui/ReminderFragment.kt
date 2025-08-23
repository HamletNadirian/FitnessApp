package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ReminderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.timeLL).setOnClickListener {
            showTimePickerDialog()
        }

        view.findViewById<View>(R.id.daysLL).setOnClickListener {
            showDaysDialog()
        }
    }

    private fun showDaysDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_reminder_day, null)
        val chipGroup = dialogView.findViewById<ChipGroup>(R.id.chipGroup)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            // Собираем выбранные чипы в момент сохранения
            val selected = chipGroup.checkedChipIds
                .map { id -> chipGroup.findViewById<Chip>(id).text.toString() }

            Log.d("Chips", "Выбраны: $selected")

            // TODO: положи selected в свою переменную / передай во ViewModel
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showTimePickerDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_reminder_time, null)
        val time = dialogView.findViewById<TimePicker>(R.id.time_picker)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        val dialog = builder.create()
        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}