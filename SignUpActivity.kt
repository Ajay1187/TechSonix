package com.example.samplekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.samplekotlin.home.MainActivity
import com.example.samplekotlin.model.User
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi




class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.bank_name)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val createAccountButton = findViewById<Button>(R.id.create_account_button)
        createAccountButton.setOnClickListener {
            saveUserInputs()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveUserInputs() {
        val externalId = findViewById<EditText>(R.id.external_id_text)

        val userInfo = User(
            externalId.text.toString(),
        )

        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
        val json = jsonAdapter.toJson(userInfo)

        val sharedPreferences = getSharedPreferences(BuildConfig.SHARED_PREF_NAME, MODE_PRIVATE)
        sharedPreferences.edit {
            putString("userInfo", json)
            apply()
        }
    }

}
