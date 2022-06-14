package com.example.gardeningservices.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.ProductAdapter
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.network.ApiUtils
import retrofit2.Call
import  retrofit2.Callback
import retrofit2.Response

class SeeAllGoodOfCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_good_of_category)

        val intent: Intent = intent

        val id = intent.getStringExtra("idCategory")

        val name = intent.getStringExtra("nameCategory")

        val title: TextView = findViewById(R.id.tv_title_good)

        title.text = name
        val recyclerView: RecyclerView = findViewById(R.id.rcV_product)
        recyclerView.setHasFixedSize(true)
        setProduct(recyclerView,id!!)

        val imageback: ImageView = findViewById(R.id.tv_back)
        imageback.setOnClickListener {
            super.onBackPressed()
        }
    }
    private fun setProduct(recyclerView: RecyclerView, id: String) {

        var productAdapter: ProductAdapter

        var listProducts: List<Products>

        val productApi = ApiUtils.createProductApi()

        productApi.getProduct(id).enqueue(object: Callback<List<Products>>{
            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Toast.makeText(this@SeeAllGoodOfCategoryActivity, "Error $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Products>>,response: Response<List<Products>>) {
                listProducts = response.body()!!
                productAdapter = ProductAdapter(this@SeeAllGoodOfCategoryActivity,listProducts)
                recyclerView.adapter = productAdapter
            }
        })

    }
}