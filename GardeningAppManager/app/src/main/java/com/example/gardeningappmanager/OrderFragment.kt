package com.example.gardeningappmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.adapter.OrderAdapter
import com.example.gardeningappmanager.model.Order
import com.example.gardeningappmanager.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    private lateinit var contextFragment: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context;
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var state: String? = null
        order_order.setOnClickListener {
            state = "order"
            val intent = Intent(this.contextFragment,OrderActivity::class.java)
            intent.putExtra("state",state)
            startActivity(intent)
        }
        order_confirm.setOnClickListener {
            state = "confirm"
            val intent = Intent(this.contextFragment,OrderActivity::class.java)
            intent.putExtra("state",state)
            startActivity(intent)
        }
        order_shipping.setOnClickListener {
            state = "shipping"
            val intent = Intent(this.contextFragment,OrderActivity::class.java)
            intent.putExtra("state",state)
            startActivity(intent)
        }
        order_success.setOnClickListener {
            state = "success"
            val intent = Intent(this.contextFragment,OrderActivity::class.java)
            intent.putExtra("state",state)
            startActivity(intent)
        }
        order_static.setOnClickListener {
            val intent = Intent(this.contextFragment,StaticActivity::class.java)
            startActivity(intent)
        }
    }

}