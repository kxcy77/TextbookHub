package com.textbookhub.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SellActivity : AppCompatActivity() {
    private var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

        findViewById<ImageButton>(R.id.btn_close).setOnClickListener {
            finish()
        }
        findViewById<MaterialCardView>(R.id.cv_image_upload).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(Intent.createChooser(intent, "Select book photo"), REQUEST_PHOTO)
        }
        findViewById<MaterialButton>(R.id.btn_publish_listing).setOnClickListener {
            if (validateListing()) {
                Toast.makeText(this, "Listing published", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK) {
            selectedPhotoUri = data?.data
            findViewById<ImageView>(R.id.iv_upload_preview).setImageURI(selectedPhotoUri)
            findViewById<TextView>(R.id.tv_upload_status).text = "Photo selected"
        }
    }

    private fun validateListing(): Boolean {
        var valid = true
        listOf(
            R.id.til_book_title to "Book title is required",
            R.id.til_author_name to "Author name is required",
            R.id.til_asking_price to "Price is required"
        ).forEach { (layoutId, message) ->
            val layout = findViewById<TextInputLayout>(layoutId)
            layout.error = null
            if (layout.editText?.text?.toString()?.trim().isNullOrEmpty()) {
                layout.error = message
                valid = false
            }
        }

        val priceText = findViewById<TextInputEditText>(R.id.et_asking_price).text?.toString()?.trim().orEmpty()
        if (priceText.isNotEmpty() && priceText.toDoubleOrNull() == null) {
            findViewById<TextInputLayout>(R.id.til_asking_price).error = "Enter a valid price"
            valid = false
        }

        if (findViewById<MaterialButtonToggleGroup>(R.id.toggle_condition).checkedButtonId == -1) {
            Toast.makeText(this, "Choose the book condition", Toast.LENGTH_SHORT).show()
            valid = false
        }

        if (selectedPhotoUri == null) {
            Toast.makeText(this, "Please upload a book photo", Toast.LENGTH_SHORT).show()
            valid = false
        }

        return valid
    }

    companion object {
        private const val REQUEST_PHOTO = 1001
    }
}
