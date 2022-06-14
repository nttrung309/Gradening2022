package com.example.gardeningservices.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gardeningservices.R
import com.example.gardeningservices.fragment.screen.OnboardingScreen1Fragment
import com.example.gardeningservices.fragment.screen.OnboardingScreen2Fragment
import com.example.gardeningservices.fragment.screen.OnboardingScreen3Fragment
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            OnboardingScreen1Fragment(),
            OnboardingScreen2Fragment(),
            OnboardingScreen3Fragment()

        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }

}