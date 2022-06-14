package com.example.gardeningservices.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.ServicesAdapter
import com.example.gardeningservices.model.Services
import androidx.recyclerview.widget.GridLayoutManager

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gardeningservices.viewmodel.ServicesViewModel
import kotlinx.android.synthetic.main.activity_see_all_services.*

class SeeAllServicesActivity : AppCompatActivity() {

    private  val servicesViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(ServicesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_services)

        rcV_services.layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false )

        servicesViewModel.CallAllServicesApi()
        observeResponseData()

        tv_back_service.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun observeResponseData() {
        servicesViewModel.listServices.observe(this, Observer {
            setUpServicesHome(it)
        })
    }

    private fun setUpServicesHome(list: List<Services>) {
        val servicesAdapter = ServicesAdapter(this@SeeAllServicesActivity, list)
        rcV_services.adapter = servicesAdapter
    }
}