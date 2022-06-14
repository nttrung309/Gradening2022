package com.example.gardeningservices.fragment.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.gardeningservices.R
import kotlinx.android.synthetic.main.fragment_onboarding_screen2.view.*


class OnboardingScreen2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding_screen2, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.next_btn.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }

}