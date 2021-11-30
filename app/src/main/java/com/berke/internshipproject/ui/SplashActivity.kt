package com.berke.internshipproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.berke.internshipproject.utils.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@SplashActivity.startActivity(MainActivity::class.java)
        finish()
    }
}