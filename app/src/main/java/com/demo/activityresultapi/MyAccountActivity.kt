package com.demo.activityresultapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityMyAccountBinding

class MyAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val input = intent.getStringExtra("Input")
        binding.tvHeader.text = input

        binding.btnSubmit.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Address", binding.edtAddress.text.toString().trim())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}