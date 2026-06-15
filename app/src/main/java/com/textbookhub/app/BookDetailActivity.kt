package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.textbookhub.app.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {
    private lateinit var book: Book
    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        book = intent.toBook()
        bindBook()

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnInquireNow.setOnClickListener {
            startActivity(Intent(this, InquiryActivity::class.java).withBook(book))
        }
        binding.btnSaveListing.setOnClickListener {
            Toast.makeText(this, "Saved listing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindBook() {
        binding.tvBookTitle.text = book.title
        binding.tvBookAuthorEdition.text = "By ${book.author}, ${book.edition}"
        binding.tvDetailCondition.text = book.condition
        binding.tvDetailCampus.text = book.campus
        binding.tvDetailPrice.text = book.price
        binding.tvAboutDescription.text = book.description
        binding.tvSellerName.text = book.sellerName
        binding.ivBookImage.setImageResource(book.imageResId)
    }

    private fun Intent.toBook(): Book {
        return Book(
            title = getStringExtra(BookExtras.TITLE) ?: "Macroeconomics 101",
            author = getStringExtra(BookExtras.AUTHOR) ?: "Olivier Blanchard",
            edition = getStringExtra(BookExtras.EDITION) ?: "8th Edition",
            courseCode = getStringExtra(BookExtras.COURSE_CODE) ?: "MADA372",
            condition = getStringExtra(BookExtras.CONDITION) ?: "Good Condition",
            price = getStringExtra(BookExtras.PRICE) ?: "R 450",
            sellerName = getStringExtra(BookExtras.SELLER_NAME) ?: "Jane Doe",
            description = getStringExtra(BookExtras.DESCRIPTION)
                ?: "Well-maintained textbook for first-year macroeconomics. No markings, slightly worn cover.",
            campus = getStringExtra(BookExtras.CAMPUS) ?: "STADIO",
            imageResId = getIntExtra(BookExtras.IMAGE_RES_ID, R.drawable.bg_rounded_placeholder)
        )
    }

    private fun Intent.withBook(book: Book): Intent {
        putExtra(BookExtras.TITLE, book.title)
        putExtra(BookExtras.AUTHOR, book.author)
        putExtra(BookExtras.EDITION, book.edition)
        putExtra(BookExtras.COURSE_CODE, book.courseCode)
        putExtra(BookExtras.CONDITION, book.condition)
        putExtra(BookExtras.PRICE, book.price)
        putExtra(BookExtras.SELLER_NAME, book.sellerName)
        putExtra(BookExtras.DESCRIPTION, book.description)
        putExtra(BookExtras.CAMPUS, book.campus)
        putExtra(BookExtras.IMAGE_RES_ID, book.imageResId)
        return this
    }
}
