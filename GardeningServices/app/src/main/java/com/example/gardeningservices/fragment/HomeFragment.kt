package com.example.gardeningservices.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningservices.R
import com.example.gardeningservices.activity.SeeAllCategoryActivity
import com.example.gardeningservices.activity.SeeAllServicesActivity
import com.example.gardeningservices.activity.SeeAllSpecialPackageActivity
import com.example.gardeningservices.adapter.CategoryAdapter
import com.example.gardeningservices.adapter.ProductAdapter
import com.example.gardeningservices.adapter.ServicesAdapter
import com.example.gardeningservices.model.Category
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.model.Services
import com.example.gardeningservices.network.ApiUtils
import com.example.gardeningservices.viewmodel.CategoryViewModel
import com.example.gardeningservices.viewmodel.ServicesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class HomeFragment: Fragment(){
    val TAG = "HomeFragment"
    public  lateinit var contextFragment: Context
    
    private  val categoryViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }
    private  val servicesViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(ServicesViewModel::class.java)
    }
    
    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        this.contextFragment = context;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return  inflater.inflate(R.layout.fragment_home, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myValue = this.arguments?.getInt("id")

        tx_see_all_categories.setOnClickListener {
            val intent = Intent(activity, SeeAllCategoryActivity::class.java)
            startActivity(intent)
        }
        tv_see_all_service.setOnClickListener {
            val intent=Intent(activity,SeeAllServicesActivity::class.java)
            startActivity(intent)
        }

        rcV_category_home.layoutManager = GridLayoutManager(contextFragment,3,GridLayoutManager.VERTICAL,false)
        categoryViewModel.CallCategoryHome()
        observeResponseCategory()

        rcV_services_home.layoutManager = GridLayoutManager(contextFragment,2,GridLayoutManager.VERTICAL,false)
        servicesViewModel.CallServicesHome()
        observeResponseServices()

        getFlashSale(rcV_sale,this.contextFragment)
    }

    private fun observeResponseServices() {
        servicesViewModel.listServicesHome.observe(viewLifecycleOwner, Observer {
            setUpServicesHome(it)
        })
    }

    private fun setUpServicesHome(it: List<Services>) {
        val servicesAdapter = ServicesAdapter(contextFragment,it)
        rcV_services_home.adapter = servicesAdapter
    }

    private fun observeResponseCategory() {
        categoryViewModel.listCategoryHome.observe(viewLifecycleOwner, Observer {
            setUpCategoryHome(it)
        })
    }

    private fun setUpCategoryHome(it: List<Category>) {
        val categoryAdapter = CategoryAdapter(contextFragment,it)
        rcV_category_home.adapter = categoryAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()

    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }
    private fun getFlashSale(recyclerView_fs: RecyclerView,context: Context ) {

        var productAdapter: ProductAdapter

        var listProduct: List<Products>

        val productApi = ApiUtils.createProductApi()

        productApi.getSaleHome().enqueue(object : Callback<List<Products>> {
            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Toast.makeText(context, "Error $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                listProduct = response.body()!!
                productAdapter = ProductAdapter(context,listProduct)
                recyclerView_fs.adapter = productAdapter;
            }

        })
    }
}