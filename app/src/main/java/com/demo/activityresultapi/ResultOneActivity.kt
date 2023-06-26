package com.demo.activityresultapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityResultOneBinding

class ResultOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultOneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val input = intent.getStringExtra("Input")
        binding.textViewResultOne.text = input

        val intent = Intent()
        intent.putExtra("Output", "Result One Received")
        setResult(RESULT_OK, intent)
    }
}