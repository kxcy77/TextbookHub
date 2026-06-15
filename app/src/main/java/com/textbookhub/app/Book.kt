package com.textbookhub.app

import androidx.annotation.DrawableRes

data class Book(
    val title: String,
    val author: String,
    val edition: String,
    val courseCode: String,
    val condition: String,
    val price: String,
    val sellerName: String,
    val description: String,
    val campus: String = "STADIO",
    @DrawableRes val imageResId: Int = R.drawable.bg_rounded_placeholder
) {
    fun matches(query: String): Boolean {
        return title.contains(query, ignoreCase = true) ||
            author.contains(query, ignoreCase = true) ||
            edition.contains(query, ignoreCase = true) ||
            courseCode.contains(query, ignoreCase = true) ||
            condition.contains(query, ignoreCase = true) ||
            description.contains(query, ignoreCase = true)
    }
}
