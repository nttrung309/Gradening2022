package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.OrderAdapter
import com.example.gardeningservices.model.Order
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    private lateinit var orderViewModel: OrderViewModel
    private var listOrder: ArrayList<Order> = arrayListOf()
    private var listItemsOrder: ArrayList<Int> = arrayListOf()
    private var idUser: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        setUp()
        getListOrder()
    }

    private fun setUp() {

        this.orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        this.idUser = intent.getIntExtra("idUser",-1)

        order_history_rcV.layoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false)

        order_history_back.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun getListOrder() {
        this.orderViewModel.getOrder(this.idUser).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setListOrder(list) }
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

    private fun setListOrder(list: List<Order>) {
        for (order in list) {
            this.listOrder.add(order)
        }
        val listIdOder: ArrayList<String> = arrayListOf()
        for (order in list) {
            listIdOder.add(order.id.toString())
        }
        if (listIdOder.isEmpty()) {
            return
        } else {
            getListItemsOrder(listIdOder)
        }
    }

    private fun getListItemsOrder(list: List<String>) {
        this.orderViewModel.getItemsOrder(list).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setListItemsOrder(list) }
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

    private fun setListItemsOrder(list: List<Int>) {
        for (items in list) {
            this.listItemsOrder.add(items)
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val orderAdapter = OrderAdapter(this, this.listOrder, this.listItemsOrder)
        order_history_rcV.adapter = orderAdapter
    }
}