package com.textbookhub.app

data class Book(
    val title: String,
    val author: String,
    val courseCode: String,
    val condition: String,
    val price: String,
    val sellerName: String
) {
    fun matches(query: String): Boolean {
        return title.contains(query, ignoreCase = true) ||
            author.contains(query, ignoreCase = true) ||
            courseCode.contains(query, ignoreCase = true) ||
            condition.contains(query, ignoreCase = true)
    }
}
