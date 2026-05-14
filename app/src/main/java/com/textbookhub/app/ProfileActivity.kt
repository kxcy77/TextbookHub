package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.appbar.MaterialToolbar

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
        findViewById<MaterialCardView>(R.id.cv_my_listings).setOnClickListener {
            startActivity(Intent(this, SellActivity::class.java))
        }
        findViewById<MaterialCardView>(R.id.cv_settings).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Settings")
                .setMessage("Notification, account, and privacy settings will be managed here.")
                .setPositiveButton("OK", null)
                .show()
        }
        findViewById<MaterialCardView>(R.id.cv_help_support).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Help & Support")
                .setMessage("For this prototype, contact support@textbookhub.local or report a listing from the book detail page.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
