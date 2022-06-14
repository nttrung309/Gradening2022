package com.example.gardeningservices.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.R
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.AddressViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.activity_address_management.*
import kotlinx.android.synthetic.main.activity_edit_address.*
import kotlinx.android.synthetic.main.activity_edit_address.btn_save_address
import java.util.regex.Pattern

class AddNewAddressActivity : AppCompatActivity() {

    private lateinit var addressViewModel: AddressViewModel
    private var  id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_address)

        this.addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        btn_save_address.setOnClickListener {
            createNewAddress()
        }
        iv_back_address_management.setOnClickListener {
            super.onBackPressed()
        }
        Log.d("idUser", intent.getIntExtra("idUser",-1).toString())
    }

    private fun createNewAddress() {
        val userFullName = edt_full_name.text.toString().trim()
        val addressProvince = edt_address_province.text.toString().trim()
        val addressDistrict = edt_district_address.text.toString().trim()
        val addressWard = edt_address_ward.text.toString().trim()
        val addressTelephone=edt_phoneNumber_address.text.toString().trim()
        val addressDetail = edt_detail_address.text.toString().trim()
        val id1 = intent.getIntExtra("idUser",-1)
        this.id = intent.getIntExtra("idUser",-1)
        if (userFullName.isNotEmpty() && addressProvince.isNotEmpty() && addressDistrict.isNotEmpty() && addressWard.isNotEmpty() && addressTelephone.isNotEmpty() && addressDetail.isNotEmpty()) {
            if (addressTelephone.length == 10) {
                this.addressViewModel.createNewAddress(
                    id1.toString(),
                    userFullName,
                    addressTelephone,
                    addressDetail,
                    addressProvince,
                    addressDistrict,
                    addressWard
                ).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { idc -> Toasty.success(this@AddNewAddressActivity,idc.message).show()}
                                super.onBackPressed()
                            }
                            Status.ERROR -> {
                                Toast.makeText(
                                    this@AddNewAddressActivity,
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            Status.LOADING -> {
                            }
                        }

                    }
                })
            } else Toasty.info(this, "Invalid Phone", Toasty.LENGTH_SHORT).show()

        } else Toasty.info(this, "Please enter full information", Toasty.LENGTH_SHORT).show()
    }
    private fun isEmailRightFormat(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}