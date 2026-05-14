package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<MaterialButton>(R.id.btn_continue_login).setOnClickListener {
            if (validateLogin()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }

        findViewById<MaterialButton>(R.id.btn_go_register).setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun validateLogin(): Boolean {
        val emailLayout = findViewById<TextInputLayout>(R.id.til_login_email)
        val passwordLayout = findViewById<TextInputLayout>(R.id.til_login_password)
        val email = findViewById<TextInputEditText>(R.id.et_login_email).text?.toString()?.trim().orEmpty()
        val password = findViewById<TextInputEditText>(R.id.et_login_password).text?.toString().orEmpty()

        emailLayout.error = null
        passwordLayout.error = null

        var valid = true
        if (email.isEmpty()) {
            emailLayout.error = "Email is required"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = "Enter a valid email address"
            valid = false
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Password is required"
            valid = false
        } else if (password.length < 6) {
            passwordLayout.error = "Password must be at least 6 characters"
            valid = false
        }

        return valid
    }
}
