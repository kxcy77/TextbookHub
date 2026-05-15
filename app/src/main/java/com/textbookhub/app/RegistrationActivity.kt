package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setupInstitutionDropdown()

        findViewById<MaterialButton>(R.id.btn_complete_registration).setOnClickListener {
            if (validateRegistration()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupInstitutionDropdown() {
        val institutions = listOf(
            "STADIO",
            "University of Cape Town",
            "University of Johannesburg",
            "University of Pretoria",
            "University of the Witwatersrand",
            "University of South Africa"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, institutions)
        findViewById<AutoCompleteTextView>(R.id.et_institution).setAdapter(adapter)
    }

    private fun validateRegistration(): Boolean {
        val fields = listOf(
            R.id.til_full_name to "Full name is required",
            R.id.til_student_number to "Student number is required",
            R.id.til_institution to "Institution is required",
            R.id.til_email to "Email is required",
            R.id.til_password to "Password is required"
        )

        var valid = true
        fields.forEach { (layoutId, message) ->
            val layout = findViewById<TextInputLayout>(layoutId)
            layout.error = null
            val editText = layout.editText
            if (editText?.text?.toString()?.trim().isNullOrEmpty()) {
                layout.error = message
                valid = false
            }
        }

        val email = findViewById<TextInputEditText>(R.id.et_email).text?.toString()?.trim().orEmpty()
        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            findViewById<TextInputLayout>(R.id.til_email).error = "Enter a valid email address"
            valid = false
        }

        val password = findViewById<TextInputEditText>(R.id.et_password).text?.toString().orEmpty()
        if (password.isNotEmpty() && password.length < 6) {
            findViewById<TextInputLayout>(R.id.til_password).error = "Password must be at least 6 characters"
            valid = false
        }

        return valid
    }
}
