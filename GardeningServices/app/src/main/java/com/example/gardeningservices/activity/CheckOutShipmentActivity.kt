package com.example.gardeningservices.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.AddressAdapter
import com.example.gardeningservices.adapter.CategoryAdapter
import com.example.gardeningservices.model.Address
import com.example.gardeningservices.model.Cart
import com.example.gardeningservices.model.CartDetail
import com.example.gardeningservices.model.Category
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.AddressViewModel
import com.example.gardeningservices.viewmodel.CartViewModel
import com.example.gardeningservices.viewmodel.OrderViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_check_out_shipment.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckOutShipmentActivity : AppCompatActivity(), AddressAdapter.AddressInterface {

    private lateinit var addressViewModel: AddressViewModel
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var cartViewModel: CartViewModel
    private var idUser: Int = 0
    private var date: Date = Date()
    private var idAddress: Int = 0
    private var total: Int = 0
    private var provision: Int = 0
    private var shipping: Int = 0
    private var idOrder: Int = 0
    private var idCart: Int = 0
    private var list: ArrayList<CartDetail> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_shipment)
        this.addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        this.orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        this.cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cart_btn_next_shipping.isClickable = false;
        cart_btn_next_shipping.isEnabled = false;

        setUp()

        tv_back_checkout_shipping.setOnClickListener {
            super.onBackPressed()
        }
        rcV_checkout_address_shipping.layoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false)
        observeResponseData()

        cart_btn_next_shipping.setOnClickListener {
            insert()
        }
        tv_back_checkout_add.setOnClickListener {
            addNewAddress()
        }
    }
    private fun observeResponseData() {
        val id1 = intent.getIntExtra("idUser",-1)
        this.addressViewModel.getListAddress(this.idUser.toString()).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { idc -> setUpCategoryHome(idc as ArrayList<Address>) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this@CheckOutShipmentActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                } }
        })
    }
    private  fun setUpCategoryHome(list: ArrayList<Address>) {
        val addressAdapter = AddressAdapter(this@CheckOutShipmentActivity, list,this)
        rcV_checkout_address_shipping.adapter = addressAdapter
    }

    private fun  setUp() {

        val sharedPreferences: SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("id", -1)

        idAddress = intent.getIntExtra("idAddress",-1)

        total = intent.getIntExtra("total", -1)

        provision = intent.getIntExtra("provision", -1)

        shipping = intent.getIntExtra("shipping", -1)

        idCart = intent.getIntExtra("idCart",-1)

        getCartDetail(idCart)

        Log.d("idUser", this.idUser.toString())
    }

    private fun getCartDetail(idCart: Int) {
        this.cartViewModel.getCartDetailByCart(idCart).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { idc -> addListCartDetail(idc) }
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

    private  fun addListCartDetail(list: List<CartDetail>) {
        for (n in list) {
            this.list.add(n)
        }
        if (list.isEmpty()) {
            return
        }
        else{
                cart_btn_next_shipping.isClickable = true;
                cart_btn_next_shipping.isEnabled = true;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun insert() {
        if(idAddress != -1){
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

            val state = "Order"
            val dateOrder = dateFormat.format(date)
            if (idAddress != 0) {
                orderViewModel.postOrder(idUser, idAddress, dateOrder, state, provision, shipping, total).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { id -> getIdOrder(id) }
                            }
                            Status.ERROR -> {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.LOADING -> {
                            }
                        }
                    }
                })
            } else Toasty.info(this, "No address selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getIdOrder(int: Int) {
        if (int == 0) {
            val intent = Intent(this, CheckOutCompleteActivity::class.java)
            intent.putExtra("idOrder", int)
            startActivity(intent)
        }
        this.idOrder = int
        insertOrderDetail(list)
    }

    private fun insertOrderDetail(list : ArrayList<CartDetail>) {
        for (cartDetail in list) {
            orderViewModel.postOrderDetail(idOrder, cartDetail.idProduct, cartDetail.quantity).observe( this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Log.d("Tag", "++")
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message + "Error", Toast.LENGTH_LONG).show()
                            Log.d("Tag", "Loi cart detail")
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        }

        val intent = Intent(this, CheckOutCompleteActivity::class.java)
        intent.putExtra("total", total)
        intent.putExtra("idOrder", idOrder)
        intent.putExtra("idCart",idCart)
        startActivity(intent)
    }

    override fun getIdAddress(idAddress: Int) {
        this.idAddress = idAddress
    }

    override fun deleteAddress(idAddress: String) {
        addressViewModel.deleteAddress(idAddress)
    }
    private fun addNewAddress() {
        val intent = Intent(this, AddNewAddressActivity::class.java)
        intent.putExtra("idUser",this.idUser)

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        observeResponseData()
    }
}