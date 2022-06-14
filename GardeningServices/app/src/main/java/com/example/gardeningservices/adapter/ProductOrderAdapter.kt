package com.example.gardeningservices.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.model.Products

class ProductOrderAdapter(private val mContext: Context, private val mList:List<Products>): RecyclerView.Adapter<ProductOrderAdapter.ProductOrderViewHolder>() {

    class ProductOrderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productNamePicture: ImageView = view.findViewById(R.id.order_history_good_image)
        val productName: TextView = view.findViewById(R.id.order_history_good_name)
        val productPrice: TextView = view.findViewById(R.id.iorder_history_good_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOrderViewHolder {
        return  ProductOrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_good_order_history, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ProductOrderViewHolder, position: Int) {
        holder.productName.text = mList[position].name
        holder.productPrice.text = mList[position].price
        Glide.with(mContext).load(mList[position].image).into(holder.productNamePicture)
    }

}