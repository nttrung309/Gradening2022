package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.ServiceDetailAdapter
import com.example.gardeningservices.adapter.ServicesAdapter
import com.example.gardeningservices.model.Services
import com.example.gardeningservices.viewmodel.ServicesViewModel
import kotlinx.android.synthetic.main.activity_click_item_services.*

class ClickItemServices : AppCompatActivity() {
    private  val servicesViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(ServicesViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_item_services)
        id_card_services

        servicesViewModel.CallServicesDetail()
        observeResponseData()
        val back: ImageView = findViewById(R.id.tv_back_service_detail)
        back.setOnClickListener {
            super.onBackPressed()
        }

    }
    private fun observeResponseData() {
        servicesViewModel.ServicesDetail.observe(this, Observer {
            setServicesDetail(it)
        })
        }

    private fun setServicesDetail(it: Services) {
        val serviceDetailAdapter = ServiceDetailAdapter(this@ClickItemServices,it)



    }
}