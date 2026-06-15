package com.textbookhub.app

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.textbookhub.app.databinding.ActivityMessagesBinding

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val messages = listOf(
            MessagePreview("Jane Doe", "Thanks, the book is still available.", "Now"),
            MessagePreview("Thabo M.", "I can meet near the library today.", "12:15"),
            MessagePreview("Aisha K.", "Would you take R480 for Calculus?", "Yesterday")
        )
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(this@MessagesActivity)
            adapter = MessageAdapter(messages) { message ->
                startActivity(Intent(this@MessagesActivity, MessageThreadActivity::class.java).apply {
                    putExtra("seller_name", message.sellerName)
                    putExtra("last_message", message.lastMessage)
                })
            }
        }
    }
}
