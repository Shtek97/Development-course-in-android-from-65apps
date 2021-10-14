package com.shtek7777.myfirstapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shtek7777.myfirstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}