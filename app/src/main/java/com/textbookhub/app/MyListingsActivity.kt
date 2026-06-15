package com.textbookhub.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.textbookhub.app.databinding.ActivityMyListingsBinding

class MyListingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyListingsBinding
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btnAddListing.setOnClickListener {
            startActivity(Intent(this, SellActivity::class.java))
        }

        bookAdapter = BookAdapter(emptyList()) {
            startActivity(Intent(this, BookDetailActivity::class.java).withBook(it))
        }
        binding.rvMyListings.layoutManager = LinearLayoutManager(this)
        binding.rvMyListings.adapter = bookAdapter
    }

    override fun onResume() {
        super.onResume()
        bindListings()
    }

    private fun bindListings() {
        val listings = BookListingStore.load(this)
        bookAdapter.submitList(listings)
        binding.tvListingCount.text = "${listings.size} active listing${if (listings.size == 1) "" else "s"}"
        binding.emptyState.visibility = if (listings.isEmpty()) View.VISIBLE else View.GONE
        binding.rvMyListings.visibility = if (listings.isEmpty()) View.GONE else View.VISIBLE
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
