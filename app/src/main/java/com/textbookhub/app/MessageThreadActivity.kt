package com.textbookhub.app

import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MessageThreadActivity : AppCompatActivity() {
    private lateinit var messageContainer: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_thread)

        val sellerName = intent.getStringExtra("seller_name") ?: "Seller"
        val lastMessage = intent.getStringExtra("last_message") ?: "Thanks, the book is still available."
        findViewById<MaterialToolbar>(R.id.toolbar).title = sellerName
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { finish() }

        messageContainer = findViewById(R.id.ll_messages)
        scrollView = findViewById(R.id.sv_messages)
        addMessageBubble(lastMessage, incoming = true)
        addMessageBubble("Great, when can we meet?", incoming = false)

        val replyInput = findViewById<TextInputEditText>(R.id.et_reply)
        replyInput.setOnClickListener {
            replyInput.requestFocus()
            val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(replyInput, InputMethodManager.SHOW_IMPLICIT)
        }

        findViewById<MaterialButton>(R.id.btn_send_reply).setOnClickListener {
            sendReply(replyInput)
        }

        replyInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendReply(replyInput)
                true
            } else {
                false
            }
        }
    }

    private fun sendReply(replyInput: EditText) {
        val reply = replyInput.text.toString().trim()
        if (reply.isEmpty()) {
            replyInput.error = "Type a reply first"
        } else {
            replyInput.error = null
            addMessageBubble(reply, incoming = false)
            replyInput.setText("")
            Toast.makeText(this, "Reply sent", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addMessageBubble(message: String, incoming: Boolean) {
        val bubble = TextView(this).apply {
            text = message
            setTextAppearance(R.style.TextAppearance_App_BodyLarge)
            setPadding(16.dp, 12.dp, 16.dp, 12.dp)
            maxWidth = 280.dp
            setBackgroundResource(if (incoming) R.drawable.bg_rounded_surface else R.drawable.bg_rounded_accent)
            if (!incoming) {
                setTextColor(getColor(R.color.md_theme_onPrimary))
            }
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = 12.dp
            gravity = if (incoming) Gravity.START else Gravity.END
        }

        messageContainer.addView(bubble, params)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}
