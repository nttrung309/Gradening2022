package com.example.gardeningappmanager.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningappmanager.OrderDetailActivity
import com.example.gardeningappmanager.R
import com.example.gardeningappmanager.Utils.Converter
import com.example.gardeningappmanager.model.Order

class OrderAdapter(private val mContext: Context, private val mList : ArrayList<Order>, private val mListItemsOrder: ArrayList<Int>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.order_id)
        val date: TextView = view.findViewById(R.id.order_date)
        val status: TextView = view.findViewById(R.id.order_state)
        val item: TextView = view.findViewById(R.id.order_item)
        val price: TextView = view.findViewById(R.id.order_price)
        val cv: ConstraintLayout = view.findViewById(R.id.order_cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.id.text = mList[position].id.toString()
        holder.date.text = Converter.convertDate(mList[position].date)
        holder.status.text = mList[position].state
        holder.item.text = mListItemsOrder[position].toString()
        holder.price.text = mList[position].total.toString()

        holder.cv.setOnClickListener {
            val intent = Intent(mContext, OrderDetailActivity::class.java)
            intent.putExtra("idOrder", mList[position].id)
            intent.putExtra("idAddress", mList[position].idAddress)
            intent.putExtra("date", mList[position].date)
            intent.putExtra("listOrderDetail", mListItemsOrder.joinToString())
            intent.putExtra("state", mList[position].state)
            mContext.startActivity(intent)
        }
    }
}


