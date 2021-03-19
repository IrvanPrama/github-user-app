package com.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val time : Long = 3000;
        Handler().postDelayed({

            val intent = Intent(SplashScreenActivity@this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, time)
    }
}