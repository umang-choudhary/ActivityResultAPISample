package com.demo.activityresultapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val requestKey = 111

    private val activityLauncher = registerForActivityResult(Contract()) { result ->
        binding.textViewData.text = result
    }

    var galleryActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val imageUri: Uri? = it.data?.data
            binding.imageView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnResult1.setOnClickListener {
            val intent = Intent(this@MainActivity, ResultOneActivity::class.java)
            intent.putExtra("Input", "Important Data")
            // deprecated
            startActivityForResult(intent, requestKey)
        }

        binding.btnResult2.setOnClickListener {
            val intent = Intent(this@MainActivity, ResultTwoActivity::class.java)
            intent.putExtra("Input", "Important Data")
            activityLauncher.launch(intent)
            //activityLauncher.launch("Important Data")
        }

        binding.btnLoadPicture.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryActivityResultLauncher.launch(galleryIntent)
        }
    }

    // deprecated
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestKey) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("Output")
                binding.textViewData.text = result
            }
        }
    }
}