package com.textbookhub.app

import androidx.lifecycle.ViewModel

class BookCatalogViewModel : ViewModel() {
    val books = listOf(
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

    fun search(query: String): List<Book> {
        val trimmedQuery = query.trim()
        return if (trimmedQuery.isEmpty()) {
            books
        } else {
            books.filter { it.matches(trimmedQuery) }
        }
    }
}
