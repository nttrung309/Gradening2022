package com.example.gardeningservices.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.model.ProductDetail
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.CartViewModel
import com.example.gardeningservices.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.fragment_cart.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private var idUser: Int = 0
    private var idCart: Int = 0
    private var idCartDetail: Int = 0
    private var quantity: Int = 0
    private var observerQuantity : MutableLiveData<Int> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        this.productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        this.cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        product_detail_add.isClickable = false;
        product_detail_add.isEnabled = false;

        val intent: Intent = intent
        val id = intent.getIntExtra("id",-1)
        val idCategory = intent.getIntExtra("idCategory", -1)
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val discount = intent.getStringExtra("discount")
        val image = intent.getStringExtra("image")
        val quantity = intent.getIntExtra("quantity", -1)
        val rating = intent.getStringExtra("rating")
        val note = intent.getStringExtra("note")

        this.quantity = quantity

        val sharedPreferences: SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("id", -1)
        setUp(name!!, price!!, discount!!, image!!, quantity, rating!!, note!!)
        getCart();

        tv_back_product_detail_to_product.setOnClickListener {
            super.onBackPressed()
        }
        product_detail_add.setOnClickListener {
            if (this.quantity >= 1) {
                cartViewModel.postAddProductToCart(idCart,id).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { idc -> getResponse(idc) }
                            }
                            Status.ERROR -> {
                                Toast.makeText(this@ProductDetailActivity, it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.LOADING -> {
                            }
                        } }
                })
            } else {
                Toast.makeText(this, "Out of Stock", Toast.LENGTH_LONG).show()
            }
        }
        setUpProfile(id, idCategory)
        setUpObserver()
    }

    private fun getResponse(cruDresponse: CRUDresponse){
        Toast.makeText(this, cruDresponse.message, Toast.LENGTH_LONG).show()
        this.quantity--
        observerQuantity.value = this.quantity
    }

    private fun setUp(name: String, price: String, discount: String, image: String, quantity: Int, rating: String, note: String) {

        product_detail_name.text = name;
        product_detail_price.text = Converter.convertMoney(price.toInt())
        product_detail_discount.text = Converter.convertMoney(discount.toInt())
        Glide.with(this@ProductDetailActivity).load(image).into(product_detail_image)
        product_detail_stock.text = quantity.toString()
        product_detail_ratingBar.rating = rating.toFloat();

    }
    private fun setUpProfile(id: Int, idCategory: Int) {
        if (idCategory == 1) {
            productViewModel.getProductDetail(id).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { idc -> setUpProgress(idc) }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this@ProductDetailActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    } }
            })
        } else {
            circularProgressBar1.visibility = View.INVISIBLE
            circularProgressBar2.visibility = View.INVISIBLE
            product_detail_stock_water.visibility = View.INVISIBLE
            product_detail_stock_light.visibility = View.INVISIBLE
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setUpProgress(productDetail: ProductDetail) {
        if (productDetail.water != "No" && productDetail.sunlight != "No") {
            product_detail_stock_water.text = productDetail.water
            product_detail_stock_light.text = productDetail.sunlight

            when (productDetail.water) {
                "Hourly" -> circularProgressBar1.progress = 100f
                "Daily" -> circularProgressBar1.progress = 75f
                "Weekly" -> circularProgressBar1.progress = 50f
                "Monthly" -> circularProgressBar1.progress = 25f
            }
            when (productDetail.sunlight) {
                "High" -> circularProgressBar2.progress = 100f
                "Medium" -> circularProgressBar2.progress = 60f
                "Low" -> circularProgressBar2.progress = 30f
            }

            circularProgressBar1.visibility = View.VISIBLE
            circularProgressBar2.visibility = View.VISIBLE
            product_detail_stock_water.visibility = View.VISIBLE
            product_detail_stock_light.visibility = View.VISIBLE
            //product_detail_stock_water2.visibility = View.VISIBLE
            //product_detail_stock_light2.visibility = View.VISIBLE
            product_detail_image_water.visibility = View.VISIBLE
            product_detail_image_light.visibility = View.VISIBLE
        } else {
            circularProgressBar1.visibility = View.INVISIBLE
            circularProgressBar2.visibility = View.INVISIBLE
            product_detail_stock_water.visibility = View.INVISIBLE
            product_detail_stock_light.visibility = View.INVISIBLE
        }
    }
    private fun getCart() {
        cartViewModel.getCartByUser(idUser).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        idCart = it.data!!.id
                        if(idCart != 0){
                            product_detail_add.isClickable = true;
                            product_detail_add.isEnabled = true;
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                } }
        })
    }
    private fun setUpObserver() {
        val nameObserver = Observer<Int> { newName ->
            product_detail_stock.text = newName.toString()
        }
        observerQuantity.observe(this,nameObserver)
    }
}