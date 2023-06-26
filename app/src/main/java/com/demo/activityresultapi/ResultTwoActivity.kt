package com.demo.activityresultapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityResultTwoBinding

class ResultTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultTwoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val input = intent.getStringExtra("Input")
        binding.textViewResultTwo.text = input

        val intent = Intent()
        intent.putExtra("Output", "Result Two Received")
        setResult(RESULT_OK, intent)
    }
}