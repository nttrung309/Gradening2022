package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.R
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.AddressViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : AppCompatActivity() {
    private  lateinit var nameEdit: TextView
    private  lateinit var phoneEdit: TextView
    private  lateinit var lineEdit: TextView
    private  lateinit var provinceEdit: TextView
    private  lateinit var districtEdit: TextView
    private  lateinit var wardEdit: TextView
    private lateinit var addressViewModel: AddressViewModel
    private var  id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)
        addressViewModel = ViewModelProvider(this@EditAddressActivity).get(AddressViewModel::class.java)

        nameEdit= findViewById(R.id.edt_Full_Name_edit)
        nameEdit.text=intent.getStringExtra("addressName")
        phoneEdit=findViewById(R.id.edt_phoneNumber_address_edit)
        phoneEdit.text=intent.getStringExtra("addressNumber")
        lineEdit=findViewById(R.id.edt_detail_address_edit)
        lineEdit.text=intent.getStringExtra("addressLine")
        provinceEdit= findViewById(R.id.edt_address_province_edit)
        provinceEdit.text=intent.getStringExtra("province")
        districtEdit= findViewById(R.id.edt_district_address_edit)
        districtEdit.text=intent.getStringExtra("district")
        wardEdit = findViewById(R.id.edt_address_ward_edit)
        wardEdit.text= intent.getStringExtra("ward")
         iv_back_address_management_2.setOnClickListener {
             super.onBackPressed()
         }
        btn_save_address.setOnClickListener {
            changeAddress()
        }
    }

    private fun changeAddress() {
        val addressName=nameEdit.text.toString().trim()
        val addressPhone=phoneEdit.text.toString().trim()
        val addressLine= lineEdit.text.toString().trim()
        val addressProvince= provinceEdit.text.toString().trim()
        val addressDistrict=districtEdit.text.toString().trim()
        val addressWard = wardEdit.text.toString().trim()
        val idAddress = intent.getIntExtra("id",-1)
        this.id = intent.getIntExtra("id",-1)
        if(addressName.isNotEmpty() && addressPhone.isNotEmpty() && addressLine.isNotEmpty() && addressProvince.isNotEmpty() && addressDistrict.isNotEmpty() &&addressWard.isNotEmpty())
        {
            addressViewModel.updateAddress(idAddress.toString(),addressName,addressPhone,addressLine,addressProvince,addressDistrict,addressWard).observe(this,
                Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                Toasty.success(this,"Success", Toast.LENGTH_LONG).show()
                                super.onBackPressed()
                            }
                            Status.ERROR -> {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.LOADING -> {
                            }
                        }
                    }
                })
        }
    }
}