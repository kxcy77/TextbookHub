package com.textbookhub.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.textbookhub.app.databinding.ItemBookCardBinding

class BookAdapter(
    books: List<Book>,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private val items = books.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(books: List<Book>) {
        items.clear()
        items.addAll(books)
        notifyDataSetChanged()
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
}
