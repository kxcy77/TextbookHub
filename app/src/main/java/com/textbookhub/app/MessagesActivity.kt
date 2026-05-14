package com.textbookhub.app

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

        val messages = listOf(
            MessagePreview("Jane Doe", "Thanks, the book is still available.", "Now"),
            MessagePreview("Thabo M.", "I can meet near the library today.", "12:15"),
            MessagePreview("Aisha K.", "Would you take R480 for Calculus?", "Yesterday")
        )
        findViewById<RecyclerView>(R.id.rv_messages).apply {
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
