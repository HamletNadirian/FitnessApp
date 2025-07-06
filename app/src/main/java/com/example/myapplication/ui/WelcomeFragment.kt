package com.example.myapplication.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        animateProgressBar(progressBar, 100, 1500) {}
        Handler(Looper.getMainLooper()).postDelayed({
            fadeOutAndNavigate(progressBar)
        }, 1500)
    }

    private fun animateProgressBar(
        progressBar: ProgressBar,
        toProgress: Int,
        i: Int,
        function: () -> Unit
    ) {
        val animation =
            ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, toProgress)
        animation.duration = 500
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    private fun fadeOutAndNavigate(progressBar: ProgressBar) {
        progressBar.animate()
            .alpha(0f)
            .setDuration(1500) // длительность исчезновения
            .withEndAction {
                findNavController().navigate(
                    R.id.action_welcomeFragment_to_dayFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.welcomeFragment, true) // удаляет splash из back stack
                        .build()
                )
            }
            .start()
    }
}