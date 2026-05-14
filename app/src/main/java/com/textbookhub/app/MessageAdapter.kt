package com.textbookhub.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(
    private val messages: List<MessagePreview>,
    private val onMessageClick: (MessagePreview) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_preview, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position], onMessageClick)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sellerName = itemView.findViewById<TextView>(R.id.tv_seller_name)
        private val lastMessage = itemView.findViewById<TextView>(R.id.tv_last_message)
        private val time = itemView.findViewById<TextView>(R.id.tv_message_time)

        fun bind(message: MessagePreview, onMessageClick: (MessagePreview) -> Unit) {
            sellerName.text = message.sellerName
            lastMessage.text = message.lastMessage
            time.text = message.time
            itemView.setOnClickListener { onMessageClick(message) }
        }
    }
}
