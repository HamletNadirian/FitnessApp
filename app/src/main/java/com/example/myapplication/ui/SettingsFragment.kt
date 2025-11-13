package com.example.myapplication.ui

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        view.findViewById<View>(R.id.item_settings_language).setOnClickListener {
            showLanguagesListDialog()
        }

        view.findViewById<View>(R.id.item_settings_reset).setOnClickListener {
            showRestartDialog()
        }
        view.findViewById<View>(R.id.item_settings_sharing).setOnClickListener {
            showSharingDialog()
        }
        view.findViewById<View>(R.id.item_settings_rate).setOnClickListener {
            showRateUs()
        }
        view.findViewById<View>(R.id.item_settings_feedback).setOnClickListener {
            showSendFeedback()
        }
        view.findViewById<View>(R.id.item_settings_policy).setOnClickListener {
            showPrivacyPolicy()
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
        dialog.show()
    }


    private fun showLanguagesListDialog(){
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_languages_list, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<CheckBox>(R.id.checkBoxEng)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("checkBoxEng", "checkBoxEng is checked: $isChecked")
            }

        dialogView.findViewById<CheckBox>(R.id.checkBoxGer)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("checkBoxGer", "Cheese is checkBoxGer: $isChecked")
            }
        dialogView.findViewById<CheckBox>(R.id.checkBoxUa)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("checkBoxUa", "Cheese is checkBoxGer: $isChecked")
            }

        dialogView.findViewById<CheckBox>(R.id.checkBoxRu)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("checkBoxRu", "Cheese is checkBoxGer: $isChecked")
            }
        dialogView.findViewById<Button>(R.id.submitButton).setOnClickListener {
            Toast.makeText(requireContext(), "Submit button clicked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            dialog.show()
        }
        dialog.show()
    }
    private fun showRestartDialog(){
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_restart_progress, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()
        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun showSharingDialog(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    private fun showRateUs(){
        val marketUri = Uri.parse("market://details?id=" + "packageName")
        //val marketUri = Uri.parse("market://details?id=" +"kidultlovin.word.zen")
        val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
        startActivity(marketIntent)
    }
    private fun showSendFeedback() {
        val email = "hamlet@email.com"
        val subject = "Feedback from user"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // только для адресов почты
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        startActivity(intent)
    }
    private fun showPrivacyPolicy(){
        val url = "https://www.example.com/privacy-policy"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

