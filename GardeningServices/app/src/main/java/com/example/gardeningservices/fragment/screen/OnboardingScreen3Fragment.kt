package com.example.gardeningservices.fragment.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.gardeningservices.R
import kotlinx.android.synthetic.main.fragment_onboarding_screen3.view.*


class OnboardingScreen3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding_screen3, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.get_started_btn.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_signIn)
            onBoardingFinish()
        }
        return view
    }

    private fun onBoardingFinish(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Get started", true)
        editor.apply()

    }

}