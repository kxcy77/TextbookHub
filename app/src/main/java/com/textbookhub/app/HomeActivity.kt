package com.textbookhub.app

import android.content.Intent
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {
    private val books = listOf(
        Book("Macroeconomics 101", "Olivier Blanchard", "MADA372", "Good Condition", "R 450", "Jane Doe"),
        Book("Introduction to Accounting", "D. Berry", "ACC101", "Like New", "R 380", "Thabo M."),
        Book("Calculus: Early Transcendentals", "James Stewart", "MATH114", "Fair Condition", "R 520", "Aisha K."),
        Book("Business Management", "S. Erasmus", "BUS100", "Good Condition", "R 300", "Liam N."),
        Book("Computer Science Basics", "J. Brookshear", "CSC101", "Like New", "R 600", "Mila P.")
    )

    private lateinit var bookAdapter: BookAdapter
    private lateinit var searchInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        createNotificationChannel()
        searchInput = findViewById(R.id.et_search)
        setupCategories()
        setupBooks()
        setupSearch()
        setupNotifications()
    }

    private fun setupCategories() {
        val categories = listOf("All", "Accounting", "Economics", "Math", "Business", "Computer Science")
        val categoryList = findViewById<RecyclerView>(R.id.rv_categories)
        categoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryList.adapter = CategoryAdapter(categories) { category ->
            val query = if (category == "All") "" else category
            searchInput.setText(query)
            filterBooks(query)
        }
    }

    private fun setupBooks() {
        bookAdapter = BookAdapter(books) {
            startActivity(Intent(this, BookDetailActivity::class.java).withBook(it))
        }

        val bookList = findViewById<RecyclerView>(R.id.rv_books)
        bookList.layoutManager = LinearLayoutManager(this)
        bookList.adapter = bookAdapter
    }

    private fun setupSearch() {
        findViewById<MaterialButton>(R.id.btn_sell).setOnClickListener {
            startActivity(Intent(this, SellActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btn_messages).setOnClickListener {
            startActivity(Intent(this, MessagesActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btn_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_search).setOnClickListener {
            filterBooks(searchInput.text.toString())
        }

        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterBooks(searchInput.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun setupNotifications() {
        findViewById<ImageView>(R.id.iv_notification_bell).setOnClickListener {
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
        putExtra(BookExtras.COURSE_CODE, book.courseCode)
        putExtra(BookExtras.CONDITION, book.condition)
        putExtra(BookExtras.PRICE, book.price)
        putExtra(BookExtras.SELLER_NAME, book.sellerName)
        return this
    }

    companion object {
        private const val CHANNEL_ID = "textbookhub_updates"
        private const val REQUEST_NOTIFICATIONS = 2001
    }
}
