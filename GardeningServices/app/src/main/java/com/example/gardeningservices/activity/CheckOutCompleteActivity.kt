package com.example.gardeningservices.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.MainActivity
import com.example.gardeningservices.R
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.CartViewModel
import com.example.gardeningservices.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_check_out_complete.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CheckOutCompleteActivity : AppCompatActivity() {


    private var total: Int = 0
    private var idOrder: Int = 0
    private lateinit var cartViewModel: CartViewModel
    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_complete)
        this.cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        total = intent.getIntExtra("total", -1)
        idOrder = intent.getIntExtra("idOrder",-1)
        val idCart = intent.getIntExtra("idCart",-1)
        if (idOrder ==0) {
            complete_state.text = "Order False"
            complete_image.setImageDrawable(getDrawable(R.drawable.ic_baseline_error_24))
            complete_total.text = ""
        }

        if (idOrder != 0 && idOrder != -1) {
            complete_state.text = "Order Success"
            complete_image.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_24))
            val convert = Converter.convertMoney(total)
            complete_total.text = "Prepare $convert to pay"
            changestate(idCart)
        }

        complete_continue.setOnClickListener {
            val intentFinish = Intent(this, MainActivity::class.java)
            startActivity(intentFinish)
            finishAffinity()
        }
    }
    private fun changestate(idCart: Int) {
        this.cartViewModel.postUpdateStateCart(idCart).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.d("Tag", "loi state")
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}