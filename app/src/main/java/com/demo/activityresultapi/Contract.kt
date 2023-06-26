package com.demo.activityresultapi

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class Contract : ActivityResultContract<String, String>() {

    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, ResultTwoActivity::class.java)
        intent.putExtra("Input", input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        var result: String? = ""
        if (resultCode == RESULT_OK) {
            result = intent?.getStringExtra("Output")
        }
        return result!!
    }
}