package com.example.gardeningservices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.model.SpecialPackage
import com.example.gardeningservices.utilities.Converter

class SpecialPackageAdapter (private val mContext: Context, private val mList:List<SpecialPackage> ): RecyclerView.Adapter<SpecialPackageAdapter.SpecialPackageViewHolder>() {
    private val selectedItem = 0

    class SpecialPackageViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val specialoffersCard: CardView = view.findViewById(R.id.cV_special_offer_1)
        val specialoffersPicture: ImageView = view.findViewById(R.id.sp_image)
        val specialoffersPercentDiscount: TextView = view.findViewById(R.id.discountpercent)
        val specialoffersDiscount : TextView = view.findViewById(R.id.discount)
        val specialoffersGetDiscount: TextView = view.findViewById(R.id.getDiscountUntil)

    }

    override fun getItemCount(): Int {
        return  mList.size
    }

    override fun onBindViewHolder(holder: SpecialPackageViewHolder, position: Int) {
        holder.specialoffersPicture.setImageBitmap(Converter(mList[position].image).DecodeToImage())
        holder.specialoffersPercentDiscount.text = mList[position].name
        holder.specialoffersDiscount.text = mList[position].price
        holder.specialoffersGetDiscount.text = mList[position].discount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialPackageViewHolder {
        return SpecialPackageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_specialpackage, parent, false))
    }


}