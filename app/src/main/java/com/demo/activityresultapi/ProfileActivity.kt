package com.demo.activityresultapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val input = intent.getStringExtra("Input")
        binding.tvHeader.text = input

        if (input == "Name Update Request") {
            binding.edtName.visibility = View.VISIBLE
            binding.edtEmail.visibility = View.GONE
        }

        binding.btnSubmit.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Name", binding.edtName.text.toString().trim())

            if (input == "Name & Email Update Request") {
                intent.putExtra("Email", binding.edtEmail.text.toString().trim())
            }

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}