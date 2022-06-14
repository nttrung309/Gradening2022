package com.example.gardeningappmanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningappmanager.Utils.Converter
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.adapter.ProductOrderAdapter
import com.example.gardeningappmanager.model.Address
import com.example.gardeningappmanager.model.Order
import com.example.gardeningappmanager.model.OrderDetail
import com.example.gardeningappmanager.model.Product
import com.example.gardeningappmanager.viewmodel.AddressViewModel
import com.example.gardeningappmanager.viewmodel.OrderViewModel
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

        order_detail_history_back.setOnClickListener {
            super.onBackPressed()
        }

//        setUpToggle()

        setUp()

        getIdProduct()

        getShipping()

        getOrder()

        setUpdate()
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

        val state = intent.getStringExtra("state")

        auto_tv_gender.setText(state)
        val itemGender = resources.getStringArray(R.array.listGender)
        val arrayGenderAdapter = ArrayAdapter<String>(this,R.layout.list_item,itemGender)
        auto_tv_gender.threshold=0
        auto_tv_gender.setAdapter(arrayGenderAdapter)
//        when (state) {
//            "order" -> toggleButton.check(R.id.button1)
//            "confirm" -> toggleButton.check(R.id.button2)
//            "shipping" -> toggleButton.check(R.id.button3)
//            "success" -> toggleButton.check(R.id.button4)
//        }

    }
    private fun getIdProduct() {
        this.orderViewModel.getIdProductByOrder(this.idOrder).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setListOrderDetail(list) }
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
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun setProduct(list: List<Product>) {
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
        order_detail_item_price.text = order.provisional_money.toString()
        order_detail_shipping_price.text = order.shipping.toString()
        val charge = order.provisional_money * 20 / 100
        order_detail_import_charges_price.text = charge.toString()
        order_detail_total_price.text = order.total.toString()
    }
    private fun setUpdate() {
        order_detail_update.setOnClickListener {
            val state = auto_tv_gender.text.toString()
            this.orderViewModel.postChangeOrderState(idOrder,state).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
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

//    private fun setUpToggle() {
//        toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
//            if (checkedId == R.id.button1) {
//                this.orderViewModel.postChangeOrderState(idOrder,"order").observe(this, Observer {
//                    it?.let { resource ->
//                        when (resource.status) {
//                            Status.SUCCESS -> {
//                            }
//                            Status.ERROR -> {
//                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                            }
//                            Status.LOADING -> {
//                            }
//                        }
//                    }
//                })
//            }
//            if (checkedId == R.id.button2) {
//                this.orderViewModel.postChangeOrderState(idOrder,"confirm").observe(this, Observer {
//                    it?.let { resource ->
//                        when (resource.status) {
//                            Status.SUCCESS -> {
//                            }
//                            Status.ERROR -> {
//                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                            }
//                            Status.LOADING -> {
//                            }
//                        }
//                    }
//                })
//            }
//            if (checkedId == R.id.button3) {
//                this.orderViewModel.postChangeOrderState(idOrder,"shipping").observe(this, Observer {
//                    it?.let { resource ->
//                        when (resource.status) {
//                            Status.SUCCESS -> {
//                            }
//                            Status.ERROR -> {
//                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                            }
//                            Status.LOADING -> {
//                            }
//                        }
//                    }
//                })
//            }
//            if (checkedId == R.id.button4) {
//                this.orderViewModel.postChangeOrderState(idOrder,"success").observe(this, Observer {
//                    it?.let { resource ->
//                        when (resource.status) {
//                            Status.SUCCESS -> {
//                            }
//                            Status.ERROR -> {
//                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                            }
//                            Status.LOADING -> {
//                            }
//                        }
//                    }
//                })
//            }
//        }
//    }

}