package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.ProductOrderAdapter
import com.example.gardeningservices.model.Address
import com.example.gardeningservices.model.Order
import com.example.gardeningservices.model.OrderDetail
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.AddressViewModel
import com.example.gardeningservices.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var orderViewModel: OrderViewModel
    private lateinit var addressViewModel: AddressViewModel

    private var idOrder: Int = 0
    private var idAddress: Int = 0
    private var date: String? = null
    private var listorderDetail: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        setUp()

//        getProduct()

        getIdProduct()

        getShipping()

        getOrder()
    }

    private fun setUp() {

        this.idOrder = intent.getIntExtra("idOrder",-1)
        this.idAddress = intent.getIntExtra("idAddress",-1)
        this.date = intent.getStringExtra("date")
        this.listorderDetail = intent.getStringExtra("listOrderDetail")

        order_detail_history_rcV.layoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

        order_detail_history_back.setOnClickListener {
            super.onBackPressed()
        }
    }
    private fun getIdProduct() {
        this.orderViewModel.getIdProductByOrder(this.idOrder).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setListOrderDetail(list) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "idOrder: " + this.idOrder.toString() + "getIdProduct Failed: " + it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
    private fun setListOrderDetail(list: List<OrderDetail>) {
        val listIdOder: ArrayList<String> = arrayListOf()
        for (order in list) {
            listIdOder.add(order.idProduct.toString())
        }
        if (listIdOder.isEmpty()) {
            return
        } else {
            getProduct(listIdOder)
        }
    }
    private fun getProduct(list: List<String>) {
        this.orderViewModel.getProductByOrder(list.joinToString()).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setProduct(list) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "getProduct failed: " + it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun setProduct(list: List<Products>) {
        val productOrderAdapter = ProductOrderAdapter(this, list)
        order_detail_history_rcV.adapter = productOrderAdapter
    }

    private fun  getShipping() {
        this.addressViewModel.getAddressById(idAddress).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setUpShipping(list) }
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

    private fun setUpShipping(address: Address) {
        order_history_date_shipping.text = Converter.convertDate(this.date!!)
        order_history_telephone_shipping.text = address.address_number
        order_history_name_shipping.text = address.address_name
        order_history_province_shipping.text = address.province
        order_history_district_shipping.text = address.district
        order_history_ward_shipping.text = address.ward
        order_history_address_shipping.text = address.address_line
    }

    private fun getOrder() {
        this.orderViewModel.getOrderById(idOrder).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setUpOrder(list) }
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

    private fun setUpOrder(order: Order) {
        order_detail_item_price.text = Converter.convertMoney(order.provisional_money)
        order_detail_shipping_price.text = Converter.convertMoney(order.shipping)
        val charge = order.provisional_money * 20 / 100
        order_detail_import_charges_price.text = Converter.convertMoney(charge)
        order_detail_total_price.text = Converter.convertMoney(order.total)
    }
}