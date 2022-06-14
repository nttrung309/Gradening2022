package com.example.gardeningservices

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gardeningservices.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val favoriteFragment = FavoriteFragment()
    private val cartFragment = CartFragment()
//    private val notificationFragment = NotificationFragment()
    private val profileFragment = ProfileFragment()
    private var idUser: Int = 0
    private var  id: Int? = null
    private var name:String?=null
    private var date:String?=null
    private var gender:String?=null
    private var telephone:String?=null
    private var email:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        makeCurrentFragment(homeFragment)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("id", -1)

        val intent: Intent = intent
        val id1 = intent.getIntExtra("idUser",-1)
        this.id = intent.getIntExtra("idUser",-1)
        val name1= intent.getStringExtra("name")
        this.name=intent.getStringExtra("name")
        val date1= intent.getStringExtra("date")
        this.date=intent.getStringExtra("date")
        val gender1= intent.getStringExtra("gender")
        this.gender=intent.getStringExtra("gender")
        val telephone1=intent.getStringExtra("telephone")
        this.telephone=intent.getStringExtra("telephone")
        val email1=intent.getStringExtra("email")
        this.email=intent.getStringExtra("email")

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.btm_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.favorite -> makeCurrentFragment(favoriteFragment)
                R.id.shoppingCart -> makeCurrentFragment(cartFragment)
//                R.id.notification -> makeCurrentFragment(notificationFragment)
                R.id.person -> makeCurrentFragment(profileFragment)
            }
            true
        }
    }
    private fun makeCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("id", this.idUser)
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
    private fun makeCurrent(fragment: Fragment,id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("id", this.idUser)
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
    private fun makeCurrent2(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("id", this.id!!)
        bundle.putString("Name",this.name!!)
        bundle.putString("Date",this.date!!)
        bundle.putString("Gender",this.gender!!)
        bundle.putString("Telephone",this.telephone!!)
        bundle.putString("Email",this.email!!)
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}