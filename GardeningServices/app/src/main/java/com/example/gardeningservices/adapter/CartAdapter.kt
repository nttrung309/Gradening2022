package com.example.gardeningservices.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardeningservices.R
import com.example.gardeningservices.model.CartDetail
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.utilities.Resource
import com.example.gardeningservices.viewmodel.CartViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers

class CartAdapter(private val mContext: Context, private var mList:ArrayList<CartDetail>, private  var mListProduct:ArrayList<Products>, private  val cartInterface: CartInterface): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_cart_image)
        val tvName: TextView = view.findViewById(R.id.item_cart_name)
        val tvPrice: TextView = view.findViewById(R.id.item_cart_price)
        val imageVDelete: ImageView = view.findViewById(R.id.item_cart_delete)
        val spinner: Spinner = view.findViewById(R.id.quantitySpinner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return  CartViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_cart,parent,false))
    }

    override fun getItemCount(): Int {
        return mListProduct.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        Glide.with(mContext).load(mListProduct[position].image).into(holder.imageView)
        holder.tvName.text = mListProduct[position].name
        holder.tvPrice.text = Converter.convertMoney(mListProduct[position].price.toInt())
        holder.spinner.setSelection(mList[position].quantity - 1)

        setUp(holder,position)
    }
    private fun setUp(holder: CartViewHolder, positionList: Int) {
        holder.imageVDelete.setOnClickListener {
            val idProduct = mListProduct[positionList].id
            val idCartDetail = mList[positionList].id
            val quantity = holder.spinner.selectedItemPosition + 1
            mList.removeAt(positionList)
            mListProduct.removeAt(positionList)
            notifyDataSetChanged()
//            cartInterface.updateQuantityProductDeleteItem(mListProduct[positionList].id, holder.spinner.selectedItemPosition + 1)
//            cartInterface.deleteItem(mList[positionList].id)
            cartInterface.updateQuantityProductDeleteItem(idProduct, quantity)
            cartInterface.deleteItem(idCartDetail)
        }
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val quantity = position + 1
                if ( quantity >= mListProduct[positionList].quantity) {
                    Toasty.info(mContext,"Out of Stock").show()
                    return
                }
                mList[positionList].quantity = quantity
                cartInterface.changeQuantity(mList[positionList].id,mListProduct[positionList].id, quantity, mList[positionList].quantity)
            }
        }
    }
    public interface CartInterface {
        fun deleteItem(idCartDetail: Int)
        fun changeQuantity(idCartDetail: Int,idProduct: Int, quantity: Int, quantityUpdate: Int)
        fun updateQuantityProductDeleteItem(idProduct: Int, quantity: Int)
    }
}