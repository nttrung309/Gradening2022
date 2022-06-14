package com.example.gardeningservices.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.gardeningservices.R
import com.example.gardeningservices.SignInActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imageView: ImageView = findViewById(R.id.image_splash)
        val annotation = AnimationUtils.loadAnimation(this, R.anim.ttb)

        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, SignInActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 1000)
    }
}