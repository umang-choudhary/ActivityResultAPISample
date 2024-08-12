package com.demo.activityresultapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.demo.activityresultapi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val requestKey = 111

    private lateinit var profileLauncher: ActivityResultLauncher<Intent>
    private lateinit var myAccountLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*
        -> Google strongly recommends using the Activity Result APIs introduced in AndroidX Activity and Fragment classes.
        -> Activity Result APIs provide a registerForActivityResult() method for registering the result callback,
        registerForActivityResult() takes an ActivityResultContract and an ActivityResultCallback and
        returns an ActivityResultLauncher, which you use to launch the other activity.
        -> While ActivityResultContracts contains a number of prebuilt ActivityResultContract classes for use.
        -> You can provide your own custom contracts that provide the precise type-safe API you need.
        */
        profileLauncher = registerForActivityResult(MyCustomContract()) { user ->
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }

        myAccountLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK && result.data != null) {
                    binding.tvAddress.text = result.data!!.getStringExtra("Address")
                }
            }

        galleryActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK && it.data != null) {
                    val imageUri: Uri? = it.data!!.data
                    binding.imageView.setImageURI(imageUri)
                }
            }

        binding.tvName.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            intent.putExtra("Input", "Name Update Request")
            // deprecated
            startActivityForResult(intent, requestKey)
        }

        binding.tvEmail.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            intent.putExtra("Input", "Name & Email Update Request")
            profileLauncher.launch(intent)
        }

        binding.tvAddress.setOnClickListener {
            val intent = Intent(this@HomeActivity, MyAccountActivity::class.java)
            intent.putExtra("Input", "Address Update Request")
            myAccountLauncher.launch(intent)
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
                val result = data?.getStringExtra("Name")
                binding.tvName.text = result
            }
        }
    }
}