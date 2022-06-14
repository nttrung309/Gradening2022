package com.example.gardeningservices.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.R
import com.example.gardeningservices.SignInActivity
import com.example.gardeningservices.activity.AddressManagement
import com.example.gardeningservices.activity.ChangePasswordActivity
import com.example.gardeningservices.activity.EditProfileActivity
import com.example.gardeningservices.activity.OrderActivity
import com.example.gardeningservices.viewmodel.CartViewModel
import com.example.gardeningservices.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment() {
    val TAG = "HomeFragment"
    private  lateinit var contextFragment: Context
    private lateinit var userViewModel: UserViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = this.arguments?.getInt("id")

        tv_edit_profile.setOnClickListener {
            val intent = Intent(activity,EditProfileActivity::class.java)
            intent.putExtra("idUser",id)
            startActivity(intent)
        }
        tv_log_out.setOnClickListener {
            val intent = Intent(activity,SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        cv_address_management.setOnClickListener {
            val intent = Intent(activity,AddressManagement::class.java)
            intent.putExtra("idUser",id)
            startActivity(intent)
        }

        profile_cv_order_history.setOnClickListener {
            val intent = Intent(activity,OrderActivity::class.java)
            intent.putExtra("idUser",id)
            startActivity(intent)
        }
        tv_change_password.setOnClickListener {
            val intent = Intent(activity,ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()

    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }
}