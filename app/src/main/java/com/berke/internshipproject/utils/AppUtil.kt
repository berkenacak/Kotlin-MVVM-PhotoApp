package com.berke.internshipproject.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun <T : AppCompatActivity> AppCompatActivity.startActivity(className: Class<T>) {
    startActivity(Intent(this, className))
}