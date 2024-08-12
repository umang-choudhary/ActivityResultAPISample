package com.demo.activityresultapi

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyCustomContract : ActivityResultContract<Intent, User>() {

    /*override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, ResultTwoActivity::class.java)
        intent.putExtra("Input", input)
        return intent
    }*/

    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): User {
        val name: String?
        val email: String?
        val user = User("", "")

        if (resultCode == RESULT_OK && intent != null) {
            name = intent.getStringExtra("Name")
            email = intent.getStringExtra("Email")

            user.name = name
            user.email = email
        }
        return user
    }
}