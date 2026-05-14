package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<MaterialButton>(R.id.btn_register).setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
