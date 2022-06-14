package com.example.gardeningservices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import es.dmoral.toasty.Toasty

class MessageAdapter(private val mContext: Context, private val mList:ArrayList<Message>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val left = 0
    private val right = 1

    private var instance: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference: DatabaseReference = instance.reference

    class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.message_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == left) {
            MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_message_left, parent, false))
        } else {
            MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_message_right, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.message.text = mList[position].message
    }

    override fun getItemViewType(position: Int): Int {
//        databaseReference.child(mList[position].idKey).get().addOnSuccessListener {
//            if (it.exists()) {
//                idSend =  it.child("idSend").value.toString()
//                Toasty.info(mContext, idSend + "uuuuu").show()
//            }
//        }.addOnFailureListener {
//            Toasty.error(mContext,"False").show()
//        }

        if (mList[position].idReceive == 0) {
            return right
        } else {
            return left
        }
    }

}