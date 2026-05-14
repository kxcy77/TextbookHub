package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class BookDetailActivity : AppCompatActivity() {
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        book = intent.toBook()
        bindBook()

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }
        findViewById<MaterialButton>(R.id.btn_inquire_now).setOnClickListener {
            startActivity(Intent(this, InquiryActivity::class.java).withBook(book))
        }
        findViewById<MaterialButton>(R.id.btn_save_listing).setOnClickListener {
            Toast.makeText(this, "Saved listing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindBook() {
        findViewById<TextView>(R.id.tv_book_title).text = book.title
        findViewById<TextView>(R.id.tv_book_author_edition).text = "By ${book.author}"
        findViewById<TextView>(R.id.tv_seller_name).text = book.sellerName
    }

    private fun Intent.toBook(): Book {
        return Book(
            title = getStringExtra(BookExtras.TITLE) ?: "Macroeconomics 101",
            author = getStringExtra(BookExtras.AUTHOR) ?: "Olivier Blanchard",
            courseCode = getStringExtra(BookExtras.COURSE_CODE) ?: "MADA372",
            condition = getStringExtra(BookExtras.CONDITION) ?: "Good Condition",
            price = getStringExtra(BookExtras.PRICE) ?: "R 450",
            sellerName = getStringExtra(BookExtras.SELLER_NAME) ?: "Jane Doe"
        )
    }

    private fun Intent.withBook(book: Book): Intent {
        putExtra(BookExtras.TITLE, book.title)
        putExtra(BookExtras.AUTHOR, book.author)
        putExtra(BookExtras.COURSE_CODE, book.courseCode)
        putExtra(BookExtras.CONDITION, book.condition)
        putExtra(BookExtras.PRICE, book.price)
        putExtra(BookExtras.SELLER_NAME, book.sellerName)
        return this
    }
}
