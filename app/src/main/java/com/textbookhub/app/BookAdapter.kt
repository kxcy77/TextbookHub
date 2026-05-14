package com.textbookhub.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    books: List<Book>,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private val items = books.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_card, parent, false)
        return BookViewHolder(view)
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

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.tv_book_title)
        private val author = itemView.findViewById<TextView>(R.id.tv_book_author)
        private val courseCode = itemView.findViewById<TextView>(R.id.tv_course_code)
        private val condition = itemView.findViewById<TextView>(R.id.tv_condition_badge)
        private val price = itemView.findViewById<TextView>(R.id.tv_book_price)
        private val sellerName = itemView.findViewById<TextView>(R.id.tv_seller_name)

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author
            courseCode.text = book.courseCode
            condition.text = book.condition
            price.text = book.price
            sellerName.text = "by ${book.sellerName}"
            itemView.setOnClickListener { onBookClick(book) }
        }
    }
}
