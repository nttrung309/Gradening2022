package com.example.gardeningservices.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.activity.EditAddressActivity
import com.example.gardeningservices.model.Address

class AddressAdapter(private val mContext: Context, private val mList : ArrayList<Address>,private val addressInterface: AddressInterface) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    var checkPosition = 0
    var idAddress: Int = 0
    class AddressViewHolder(view: View) : RecyclerView.ViewHolder (view){
        val name : TextView = view.findViewById(R.id.address_name)
        val number : TextView = view.findViewById(R.id.address_phone)
        val address : TextView = view.findViewById(R.id.address_add)
        val province : TextView = view.findViewById(R.id.address_province)
        val district : TextView = view.findViewById(R.id.address_district)
        val ward : TextView = view.findViewById(R.id.address_ward)
        val btnEdit : AppCompatButton = view.findViewById(R.id.address_btn_edit)
        val btnDelete : ImageView = view.findViewById(R.id.address_btn_delete)
        val cv : ConstraintLayout = view.findViewById(R.id.address_cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        holder.name.text = mList[position].address_name
        holder.number.text = mList[position].address_number
        holder.address.text = mList[position].address_line
        holder.province.text = mList[position].province
        holder.district.text = mList[position].district
        holder.ward.text = mList[position].ward

        if (checkPosition == position) {
            holder.cv.background = mContext.getDrawable(R.drawable.corner_address_select)
            addressInterface.getIdAddress(mList[position].id)
        } else {
            holder.cv.background = mContext.getDrawable(R.drawable.corner_address)
        }
        setUpEvent(holder, position)
    }

    private fun setUpEvent(holder: AddressViewHolder, position: Int) {

        holder.btnDelete.setOnClickListener {
            addressInterface.deleteAddress(mList[position].id.toString())
            mList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnEdit.setOnClickListener {
            val intent = Intent(mContext, EditAddressActivity::class.java)
            intent.putExtra("id",mList[position].id)
            intent.putExtra("addressName",mList[position].address_name)
            intent.putExtra("addressNumber",mList[position].address_number)
            intent.putExtra("addressLine",mList[position].address_line)
            intent.putExtra("province",mList[position].province)
            intent.putExtra("district",mList[position].district)
            intent.putExtra("ward",mList[position].ward)
            mContext.startActivity(intent)

        }
        holder.cv.setOnClickListener {
            val pre = checkPosition
            checkPosition = position

            addressInterface.getIdAddress(mList[position].id)

            notifyItemChanged(pre)
            notifyItemChanged(checkPosition)
        }
    }
    public interface AddressInterface {
        fun getIdAddress(idAddress: Int)
        fun deleteAddress(id :String)
    }
}