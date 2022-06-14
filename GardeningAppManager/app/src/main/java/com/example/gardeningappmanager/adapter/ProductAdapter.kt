package com.example.gardeningappmanager.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningappmanager.ProductDetailActivity
import com.example.gardeningappmanager.R
import com.example.gardeningappmanager.model.Product

class ProductAdapter(private val mContext: Context, private val mList:List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_good,parent,false))
    }
    override fun getItemCount(): Int {
        return  mList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productName.text = mList[position].name
        Glide.with(mContext).load(mList[position].image).into(holder.productNamePicture)

        holder.cv.setOnClickListener {
            val intent = Intent(mContext, ProductDetailActivity::class.java)
            intent.putExtra("idProduct",mList[position].id)
            intent.putExtra("name",mList[position].name)
            intent.putExtra("price",mList[position].price)
            intent.putExtra("quantity",mList[position].quantity)
            intent.putExtra("idCategory",mList[position].idCategory)
            intent.putExtra("image",mList[position].image)
            mContext.startActivity(intent)
        }
    }
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productNamePicture: ImageView = view.findViewById(R.id.item_good_image)
        val productName: TextView = view.findViewById(R.id.item_good_name)
        val cv: LinearLayout = view.findViewById(R.id.item_good_cv)
    }

}