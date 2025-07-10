package com.example.myapplication.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Exercise
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExerciseDetailBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_NAME = "arg_name"

        private const val ARG_DESCRIPTION = "arg_description"
        private const val ARG_DURATION = "arg_duration"
        private const val ARG_GIF_RES_ID = "arg_gif_res_id"

        fun newInstance(exercise: Exercise): ExerciseDetailBottomSheet {
            val args = Bundle().apply {
                putString(ARG_NAME, exercise.name)
                putString(ARG_DESCRIPTION, exercise.description)
                putInt(ARG_DURATION, exercise.durationSeconds)
                putInt(ARG_GIF_RES_ID, exercise.gifResId)
            }
            val fragment = ExerciseDetailBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_exercise_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tvName: TextView = view.findViewById(R.id.tvExerciseName)
        val tvDescription: TextView = view.findViewById(R.id.tvDescriptionExercise)
        val tvDuration: TextView = view.findViewById(R.id.tvExerciseDuration)
        val ivGif: ImageView = view.findViewById(R.id.ivExerciseGif)

        arguments?.let {
            tvName.text = it.getString(ARG_NAME)
            tvDescription.text = it.getString(ARG_DESCRIPTION)
            tvDuration.text = "${it.getInt(ARG_DURATION)} секунд"
            val gifResId = it.getInt(ARG_GIF_RES_ID)
            if (gifResId != 0) {
                Glide.with(this)
                    .asGif()
                    .load(gifResId)

                    .into(ivGif)
            }
        }
    }
}
