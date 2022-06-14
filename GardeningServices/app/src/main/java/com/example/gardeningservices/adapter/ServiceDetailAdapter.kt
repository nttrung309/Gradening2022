package com.example.gardeningservices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.model.Services
import com.example.gardeningservices.utilities.Converter

class ServiceDetailAdapter (private val mContext: Context, private val information: Services):RecyclerView.Adapter<ServiceDetailAdapter.ServiceDetailViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceDetailAdapter.ServiceDetailViewHolder {
        return ServiceDetailAdapter.ServiceDetailViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.activity_click_item_services, parent, false) as CardView
        )
    }

    override fun onBindViewHolder(holder: ServiceDetailAdapter.ServiceDetailViewHolder, position: Int) {
        holder.servicesName.text=information.name
        holder.servicesPrice.text=information.price
        holder.servicesRating.numStars= information.rating.toInt()
        //holder.servicesStockIn.text=information.stock_in
        holder.servicesPicture.setImageBitmap(Converter(information.image).DecodeToImage())
        holder.servicesPicture.setColorFilter(ContextCompat.getColor(mContext,R.color.Green1))
    }
    class ServiceDetailViewHolder(view: CardView):RecyclerView.ViewHolder(view) {
        val servicesName: TextView = view.findViewById(R.id.id_tv_service_name_SD)
        val servicesPicture: ImageView = view.findViewById(R.id.id_im_SD)
        val servicesRating: RatingBar = view.findViewById(R.id.id_ratingBar)
        val servicesPrice: TextView =view.findViewById(R.id.id_tv_price_SD)
        val servicesStockIn: TextView =view.findViewById(R.id.id_tc_stock_in_SD)
    }
    override fun getItemCount(): Int {
        return 1;
    }

}