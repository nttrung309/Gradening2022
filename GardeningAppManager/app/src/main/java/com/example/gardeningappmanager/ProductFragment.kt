package com.example.gardeningappmanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.adapter.ProductAdapter
import com.example.gardeningappmanager.model.Product
import com.example.gardeningappmanager.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*


class ProductFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var contextFragment: Context
    private var listProduct: ArrayList<Product> = arrayListOf()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        product_rcV.layoutManager = GridLayoutManager(contextFragment,1, GridLayoutManager.VERTICAL,false)
        getListProduct()
    }

    private fun getListProduct() {
        listProduct.clear()
        this.productViewModel.getProduct().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { idc -> setListProduct(idc)}
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                                this.contextFragment,
                                it.message,
                                Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {
                    }
                }

            }
        })
    }

    private fun setListProduct(list: List<Product>) {
        for (n in list) {
            this.listProduct.add(n)
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val productAdapter = ProductAdapter(this.contextFragment,this.listProduct)
        product_rcV.adapter = productAdapter
    }
}