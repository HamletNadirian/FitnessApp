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
import com.example.myapplication.R

class ProfileFragment : Fragment() {
//TODO Надо будет добавить сохрнаять --> Shared Preferences?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<View>(R.id.height_edt).setOnClickListener {
            showHeightDialog()
        }
       view.findViewById<View>(R.id.weight_edt).setOnClickListener {
            showWeightDialog()
        }
        view.findViewById<View>(R.id.target_weight_edt).setOnClickListener {
            showTargetWeightDialog()
        }
    }
    private fun showHeightDialog(){
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_height, null)

        val npCm = dialogView.findViewById<NumberPicker>(R.id.np_cm)
        npCm.minValue = 100
        npCm.maxValue = 250
        npCm.value = 178


        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val kg = npCm.value
            view?.findViewById<TextView>(R.id.tv_height)?.text = "${kg} kg"
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showWeightDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_weight, null)

        val npKg = dialogView.findViewById<NumberPicker>(R.id.np_kg)
        npKg.minValue = 50
        npKg.maxValue = 150
        npKg.value = 62

        val npMg = dialogView.findViewById<NumberPicker>(R.id.np_mg)
        npMg.minValue = 0
        npMg.maxValue = 9
        npMg.value = 1

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val kg = npKg.value
            val mg = npMg.value
            view?.findViewById<TextView>(R.id.tv_weight)?.text = "${kg}.${mg} kg"
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun showTargetWeightDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_target_weight, null)

        val npKg = dialogView.findViewById<NumberPicker>(R.id.np_kg_target)
        npKg.minValue = 50
        npKg.maxValue = 150
        npKg.value = 62

        val npMg = dialogView.findViewById<NumberPicker>(R.id.np_mg_target)
        npMg.minValue = 0
        npMg.maxValue = 9
        npMg.value = 1

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val kg = npKg.value
            val mg = npMg.value
            view?.findViewById<TextView>(R.id.tv_target_weight)?.text = "${kg}.${mg} kg"
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun dismissDialog(dialog: AlertDialog) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}