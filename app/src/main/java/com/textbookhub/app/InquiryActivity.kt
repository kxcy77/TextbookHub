package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InquiryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquiry)

        val bookTitle = intent.getStringExtra(BookExtras.TITLE) ?: "Macroeconomics 101"
        val bookPrice = intent.getStringExtra(BookExtras.PRICE) ?: "R 450"
        findViewById<TextView>(R.id.tv_book_title).text = bookTitle
        findViewById<TextView>(R.id.tv_book_price).text = bookPrice

        val messageInput = findViewById<TextInputEditText>(R.id.et_message)
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
        findViewById<MaterialButton>(R.id.btn_send_message).setOnClickListener {
            if (messageInput.text.isNullOrBlank()) {
                messageInput.setText("Hi, I am interested in $bookTitle. Is it still available?")
            }
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MessagesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
