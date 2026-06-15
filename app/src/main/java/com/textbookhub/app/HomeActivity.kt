package com.textbookhub.app

import android.content.Intent
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.textbookhub.app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val books = listOf(
        Book(
            title = "Macroeconomics 101",
            author = "Olivier Blanchard",
            edition = "8th Edition",
            courseCode = "ECO101",
            condition = "Good Condition",
            price = "R 450",
            sellerName = "Jane Doe",
            description = "Well-maintained first-year macroeconomics textbook with light cover wear."
        ),
        Book(
            title = "Introduction to Accounting",
            author = "D. Berry",
            edition = "2nd Edition",
            courseCode = "ACC101",
            condition = "Like New",
            price = "R 380",
            sellerName = "Thabo M.",
            description = "Clean accounting textbook suitable for first-year financial accounting modules."
        ),
        Book(
            title = "Calculus: Early Transcendentals",
            author = "James Stewart",
            edition = "9th Edition",
            courseCode = "MATH114",
            condition = "Fair Condition",
            price = "R 520",
            sellerName = "Aisha K.",
            description = "Includes calculus examples, practice problems, and marked revision sections."
        ),
        Book(
            title = "Business Management",
            author = "S. Erasmus",
            edition = "6th Edition",
            courseCode = "BUS100",
            condition = "Good Condition",
            price = "R 300",
            sellerName = "Liam N.",
            description = "Introductory business management textbook for commerce students."
        ),
        Book(
            title = "Computer Science Basics",
            author = "J. Brookshear",
            edition = "13th Edition",
            courseCode = "CSC101",
            condition = "Like New",
            price = "R 600",
            sellerName = "Mila P.",
            description = "Foundational computer science textbook covering algorithms and data representation."
        )
    )

    private lateinit var bookAdapter: BookAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        setupCategories()
        setupBooks()
        setupSearch()
        setupNotifications()
    }

    private fun setupCategories() {
        val categories = listOf("All", "Accounting", "Economics", "Math", "Business", "Computer Science")
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = CategoryAdapter(categories) { category ->
            val query = if (category == "All") "" else category
            binding.etSearch.setText(query)
            filterBooks(query)
        }
    }

    private fun setupBooks() {
        bookAdapter = BookAdapter(books) {
            startActivity(Intent(this, BookDetailActivity::class.java).withBook(it))
        }

        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = bookAdapter
    }

    private fun setupSearch() {
        binding.btnSell.setOnClickListener {
            startActivity(Intent(this, SellActivity::class.java))
        }
        binding.btnMessages.setOnClickListener {
            startActivity(Intent(this, MessagesActivity::class.java))
        }
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.btnSearch.setOnClickListener {
            filterBooks(binding.etSearch.text.toString())
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterBooks(binding.etSearch.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun setupNotifications() {
        binding.ivNotificationBell.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_NOTIFICATIONS)
            } else {
                showTextbookNotification()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_NOTIFICATIONS) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                showTextbookNotification()
            } else {
                Toast.makeText(this, "Notifications are disabled for TextbookHub", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "TextbookHub updates",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Book listing and message updates"
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun showTextbookNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_bell)
            .setContentTitle("New textbook match")
            .setContentText("A seller has a book that matches your search.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(100, notification)
        Toast.makeText(this, "Notification sent", Toast.LENGTH_SHORT).show()
    }

    private fun filterBooks(query: String) {
        val trimmedQuery = query.trim()
        val filteredBooks = if (trimmedQuery.isEmpty()) {
            books
        } else {
            books.filter { it.matches(trimmedQuery) }
        }

        bookAdapter.submitList(filteredBooks)

        val message = if (filteredBooks.isEmpty()) {
            "No books found"
        } else {
            "${filteredBooks.size} book${if (filteredBooks.size == 1) "" else "s"} found"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    companion object {
        private const val CHANNEL_ID = "textbookhub_updates"
        private const val REQUEST_NOTIFICATIONS = 2001
    }
}
