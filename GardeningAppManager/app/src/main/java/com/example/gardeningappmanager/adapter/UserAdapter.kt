package com.example.gardeningappmanager.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningappmanager.R
import com.example.gardeningappmanager.model.User
import com.example.gardeningappmanager.view.ChatActivity

class UserAdapter(private val mContext: Context, private val mList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.user_name)
        val number: TextView = view.findViewById(R.id.user_telephone)
        val cv: CardView = view.findViewById(R.id.user_cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = mList[position].name
        holder.number.text = mList[position].telephone

        holder.cv.setOnClickListener {
            val intent = Intent(mContext, ChatActivity::class.java)
            intent.putExtra("idUser", mList[position].id)
            mContext.startActivity(intent)
        }
    }
}