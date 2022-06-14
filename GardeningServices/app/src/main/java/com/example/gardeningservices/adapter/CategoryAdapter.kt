package com.example.gardeningservices.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.activity.SeeAllGoodOfCategoryActivity
import com.example.gardeningservices.model.Category
import com.example.gardeningservices.utilities.Converter

class CategoryAdapter(private val mContext: Context, private val mList:List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_category,parent,false))
    }

    override fun getItemCount(): Int {
        return  mList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.text = mList[position].name
        Glide.with(mContext).load(mList[position].image).into(holder.categoryPicture)
        holder.categoryPicture.setColorFilter(ContextCompat.getColor(mContext,R.color.Green1))
        holder.categoryCard.setOnClickListener {
            val intent = Intent(mContext,SeeAllGoodOfCategoryActivity::class.java)
            intent.putExtra("idCategory",mList[position].id)
            intent.putExtra("nameCategory",mList[position].name)
            mContext.startActivity(intent)
        }
    }

    class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.tv_name)
        val categoryPicture: ImageView = view.findViewById(R.id.iv_image)
        val categoryCard: CardView = view.findViewById(R.id.cv_category)
    }

}