package com.example.gardeningappmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.model.Order
import com.example.gardeningappmanager.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_static.*

class StaticActivity : AppCompatActivity() {

    private lateinit var orderViewModel: OrderViewModel
    private var order: Int =0
    private var confirm: Int =0
    private var shipping: Int =0
    private var success: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static)
        this.orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        getOrder()
    }
    private fun getOrder() {
        this.orderViewModel.getOrderByState("order").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setOrder(list) }
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
    private fun getConfirm() {
        this.orderViewModel.getOrderByState("confirm").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setConfirm(list) }
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
    private fun getShipping() {
        this.orderViewModel.getOrderByState("shipping").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setShipping(list) }
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
    private fun getSuccess() {
        this.orderViewModel.getOrderByState("success").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> setSuccess(list) }
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
    private fun setOrder(list: List<Order>) {
        if (!list.isNullOrEmpty()) {
            this.order = list.size
        }
        getConfirm()
    }
    private fun setConfirm(list: List<Order>) {
        if (!list.isNullOrEmpty()) {
            this.confirm = list.size
        }
        getShipping()
    }
    private fun setShipping(list: List<Order>) {
        if (!list.isNullOrEmpty()) {
            this.shipping = list.size
        }
        getSuccess()
    }
    private fun setSuccess(list: List<Order>) {
        if (!list.isNullOrEmpty()) {
            this.success = list.size
        }
        setUp()
    }
    private fun setUp() {
        val pie: Pie = AnyChart.pie()
        val dataEntries: ArrayList<DataEntry> = ArrayList()
        dataEntries.add(ValueDataEntry("Order", this.order))
        dataEntries.add(ValueDataEntry("Confirm", this.confirm))
        dataEntries.add(ValueDataEntry("Shipping", this.shipping))
        dataEntries.add(ValueDataEntry("Success", this.success))
        pie.data(dataEntries)
        anyChart.setChart(pie)

        val total = this.order + this.confirm + this.shipping + this.success
        allOrders.text = total.toString()
        waitingOrder.text = this.order.toString()
        confirmedOrders.text = this.confirm.toString()
        deliveringOrders.text = this.shipping.toString()
        completeOrders.text = this.success.toString()
    }
}