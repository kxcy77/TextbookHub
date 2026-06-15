package com.textbookhub.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.textbookhub.app.databinding.ItemMessagePreviewBinding

class MessageAdapter(
    private val messages: List<MessagePreview>,
    private val onMessageClick: (MessagePreview) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessagePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position], onMessageClick)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(
        private val binding: ItemMessagePreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessagePreview, onMessageClick: (MessagePreview) -> Unit) {
            binding.tvSellerName.text = message.sellerName
            binding.tvLastMessage.text = message.lastMessage
            binding.tvMessageTime.text = message.time
            binding.root.setOnClickListener { onMessageClick(message) }
        }
    }
}
