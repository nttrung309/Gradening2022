package com.example.gardeningservices.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.activity.ProductDetailActivity
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.utilities.Converter

class ProductAdapter(private val mContext: Context, private val mList:List<Products>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_good,parent,false))
    }
    override fun getItemCount(): Int {
        return  mList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productName.text = mList[position].name
        holder.productPrice.text = Converter.convertMoney(mList[position].price.toInt())
        Glide.with(mContext).load(mList[position].image).into(holder.productNamePicture)
        holder.productCard.setOnClickListener {
            val intent = Intent(mContext, ProductDetailActivity::class.java)
            intent.putExtra("id",mList[position].id)
            intent.putExtra("idCategory",mList[position].idCategory)
            intent.putExtra("name",mList[position].name)
            intent.putExtra("price",mList[position].price)
            intent.putExtra("discount",mList[position].discount)
            intent.putExtra("image",mList[position].image)
            intent.putExtra("quantity",mList[position].quantity)
            intent.putExtra("rating",mList[position].rating)
            intent.putExtra("note",mList[position].note)
            mContext.startActivity(intent)
        }
    }
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productNamePicture: ImageView = view.findViewById(R.id.tv_image_good)
        val productName: TextView = view.findViewById(R.id.tv_name_good)
        val productPrice: TextView = view.findViewById(R.id.tv_price_good)
        val productCard: CardView = view.findViewById(R.id.cv_category)
    }

}