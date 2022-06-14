package com.example.gardeningappmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.adapter.OrderAdapter
import com.example.gardeningappmanager.model.Address
import com.example.gardeningappmanager.model.Order
import com.example.gardeningappmanager.viewmodel.OrderViewModel
import com.example.gardeningappmanager.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    private lateinit var orderViewModel: OrderViewModel
    private var state: String? = null
    private var listOrder: ArrayList<Order> = arrayListOf()
    private var listItemsOrder: ArrayList<Int> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        this.orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        order_rcV.layoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false)
        this.state = intent.getStringExtra("state")
        product_name.text = this.state
        tv_back_order_to_product.setOnClickListener {
            super.onBackPressed()
        }
        getOrder(state!!)
    }

    private fun getOrder(s: String){
        this.orderViewModel.getOrderByState(s).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setUp(list as ArrayList<Order>) }
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
    private fun setUp(list : ArrayList<Order>) {
        val listOrder: ArrayList<Order> = arrayListOf()
        for (order in list) {
            listOrder.add(order)
        }
        val listIdOder: ArrayList<String> = arrayListOf()
        for (order in list) {
            listIdOder.add(order.id.toString())
        }
        if (listIdOder.isEmpty()) {
            return
        } else {
            getListItemsOrder(listIdOder, listOrder)
        }
    }
    private fun getListItemsOrder(list: List<String>,list1: ArrayList<Order>) {
        this.orderViewModel.getItemsOrder(list).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setListItemsOrder(list,list1) }
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

    private fun setListItemsOrder(list: List<Int>,list1: ArrayList<Order>) {
        for (items in list) {
            this.listItemsOrder.add(items)
        }
        setUpRecyclerView(list1)
    }

    private fun setUpRecyclerView(list1: ArrayList<Order>) {
        val orderAdapter = OrderAdapter(this, list1, this.listItemsOrder)
        order_rcV.adapter = orderAdapter
        Log.d("Order", "+")
    }

    override fun onResume() {
        super.onResume()
        getOrder(state!!)
    }
}