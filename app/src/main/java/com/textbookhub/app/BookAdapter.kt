package com.textbookhub.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.textbookhub.app.databinding.ItemBookCardBinding

class BookAdapter(
    books: List<Book>,
    private val onBookClick: (Book) -> Unit
) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback) {
    init {
        submitList(books)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookViewHolder(
        private val binding: ItemBookCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = "${book.author} - ${book.edition}"
            binding.tvCourseCode.text = book.courseCode
            binding.tvConditionBadge.text = book.condition
            binding.tvBookPrice.text = book.price
            binding.tvSellerName.text = "by ${book.sellerName}"
            binding.ivBookThumbnail.setImageResource(book.imageResId)
            binding.root.setOnClickListener { onBookClick(book) }
        }
    }

    private object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.title == newItem.title && oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}
