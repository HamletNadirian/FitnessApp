package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        val reminderLayout = view.findViewById<View>(R.id.my_reminder_layout)
        reminderLayout.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_reminderFragment)
        }

    }

}