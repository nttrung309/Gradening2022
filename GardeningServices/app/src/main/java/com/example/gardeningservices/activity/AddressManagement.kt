package com.example.gardeningservices.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.AddressAdapter
import com.example.gardeningservices.model.Address
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.AddressViewModel
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.activity_address_management.*
import kotlinx.android.synthetic.main.activity_check_out_shipment.*

class AddressManagement : AppCompatActivity(),AddressAdapter.AddressInterface  {
    private lateinit var addressViewModel: AddressViewModel
    private var idAddress: Int = 0
    private var idUser: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_management)

        this.addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        rcV_address_management.layoutManager= GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        loadData()
        iv_back_profile.setOnClickListener {
            super.onBackPressed()
        }
        idUser = intent.getIntExtra("idUser",-1)
        btn_new_address.setOnClickListener {
            val intent = Intent(this@AddressManagement,AddNewAddressActivity::class.java)
            intent.putExtra("idUser",idUser)
            startActivity(intent)
        }
    }

    private fun loadData() {
        idUser = intent.getIntExtra("idUser",-1)
        this.addressViewModel.getListAddress(idUser.toString()).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { idc -> setUpCategoryHome(idc as ArrayList<Address>) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this@AddressManagement, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                } }
        })
    }
    private  fun setUpCategoryHome(list: ArrayList<Address>) {
        val addressAdapter = AddressAdapter(this@AddressManagement, list,this)
        rcV_address_management.adapter = addressAdapter
    }

    override fun getIdAddress(idAddress: Int) {
        this.idAddress = idAddress
    }

    override fun deleteAddress(idAddress: String) {
        addressViewModel.deleteAddress(idAddress)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}