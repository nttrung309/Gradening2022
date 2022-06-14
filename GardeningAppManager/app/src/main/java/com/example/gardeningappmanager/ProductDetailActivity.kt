package com.example.gardeningappmanager

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.viewmodel.ProductViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    private var idProduct: Int? = null
    private var idCategory: Int? = null
    private var name: String? = null
    private var price: String? = null
    private var quantity: Int? = null
    private var image: String? =null

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        tv_back_product_detail_to_product.setOnClickListener {
            super.onBackPressed()
        }

        product_detail_add.isEnabled = false
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        idProduct = intent.getIntExtra("idProduct",-1)
        idCategory = intent.getIntExtra("idCategory",-1)
        name = intent.getStringExtra("name")
        price = intent.getStringExtra("price")
        quantity = intent.getIntExtra("quantity",-1)
        image = intent.getStringExtra("image")
        setUp()

        product_detail_add.setOnClickListener {
            val name = item_good_name.text.toString().trim()
            val price = item_good_price.text.toString().trim()
            val quantity = item_good_quantity.text.toString().trim()
            if (name.isNotEmpty() && price.isNotEmpty() && quantity.isNotEmpty()) {
                productViewModel.postUpdateProduct(quantity.toInt(), price.toInt(), name, idProduct!!).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { idc -> toast(idc)}
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
            toast(true);
        }
    }
    private fun toast(b: Boolean) {
        if (b) Toasty.success(this,"Cập nhập thành công").show()
    }
    private fun setUp() {
        Glide.with(this).load(image).into(product_detail_image)
        item_good_name.setText(name)
        item_good_price.setText(price.toString())
        item_good_quantity.setText(quantity.toString())

        item_good_name.addTextChangedListener {
            product_detail_add.isEnabled = it.toString() != name || item_good_price.text.toString() != price || item_good_quantity.text.toString() != quantity.toString()
        }
        item_good_price.addTextChangedListener {
            product_detail_add.isEnabled = it.toString() != price || item_good_name.text.toString() != name || item_good_quantity.text.toString() != quantity.toString()
        }
        item_good_quantity.addTextChangedListener {
            product_detail_add.isEnabled = it.toString() != quantity.toString() || item_good_name.text.toString() != name || item_good_price.text.toString() != price
        }

    }
}