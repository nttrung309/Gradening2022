package com.example.gardeningservices.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.activity.ProductDetailActivity
import com.example.gardeningservices.model.Services
import com.example.gardeningservices.utilities.Converter

class ServicesAdapter(private val mContext: Context, private val mList:List<Services>):RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        return ServicesViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_services,parent,false))
    }
    override fun getItemCount(): Int {
        return mList.size
    }
     override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.servicesName.text=mList[position].name
        holder.servicesPrice.text=mList[position].price
        Glide.with(mContext).load(mList[position].image).into(holder.servicesPicture)
        holder.servicesPicture.setColorFilter(ContextCompat.getColor(mContext,R.color.Green1))
        holder.servicesCard.setOnClickListener {
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
    class ServicesViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val servicesName: TextView = view.findViewById(R.id.tv_name_sv)
        val servicesPicture: ImageView = view.findViewById(R.id.iv_im_sv)
        val servicesPrice: TextView=view.findViewById(R.id.tv_price_sv)
        val servicesCard: CardView = view.findViewById(R.id.cv_services)
    }

}