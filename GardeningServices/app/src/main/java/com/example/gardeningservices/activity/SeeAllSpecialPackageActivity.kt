package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.SpecialPackageAdapter
import com.example.gardeningservices.model.SpecialPackage
import com.example.gardeningservices.viewmodel.SpecialPackageViewModel

class SeeAllSpecialPackageActivity : AppCompatActivity() {
    private lateinit var recycler_view : RecyclerView

    private val packageViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(SpecialPackageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_specialpackage)
        recycler_view = findViewById(R.id.rcV_special_package)

        val imageback: ImageView = findViewById(R.id.tv_back_package)
        imageback.setOnClickListener {
            super.onBackPressed()
        }

        packageViewModel.CallAllSpecialPackageApi()
        observeResponseData()
    }

    private fun observeResponseData() {
        packageViewModel.listPackage.observe(this, Observer {
            setUpSpecialPackageHome(it)
        })
    }

    private  fun setUpSpecialPackageHome(list: List<SpecialPackage>) {
        val specialPackageAdapter = SpecialPackageAdapter(this@SeeAllSpecialPackageActivity, list)
        recycler_view.adapter = specialPackageAdapter
        Toast.makeText(this, recycler_view.childCount.toString(),Toast.LENGTH_LONG).show()
    }

}