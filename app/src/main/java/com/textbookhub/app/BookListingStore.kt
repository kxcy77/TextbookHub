package com.textbookhub.app

import android.content.Context

object BookListingStore {
    private const val PREFS_NAME = "textbookhub_listings"
    private const val KEY_LISTINGS = "listings"
    private const val FIELD_SEPARATOR = "|"
    private const val RECORD_SEPARATOR = "\n"

    fun save(context: Context, book: Book) {
        val books = load(context).toMutableList()
        books.add(0, book)
        persist(context, books)
    }

    fun load(context: Context): List<Book> {
        val raw = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LISTINGS, "")
            .orEmpty()

        if (raw.isBlank()) return emptyList()

        return raw.lines()
            .filter { it.isNotBlank() }
            .mapNotNull { line ->
                val fields = line.split(FIELD_SEPARATOR).map(::decode)
                if (fields.size < 8) {
                    null
                } else {
                    Book(
                        title = fields[0],
                        author = fields[1],
                        edition = fields[2],
                        courseCode = fields[3],
                        condition = fields[4],
                        price = fields[5],
                        sellerName = fields[6],
                        description = fields[7],
                        campus = fields.getOrElse(8) { "STADIO" }
                    )
                }
            }
    }

    fun count(context: Context): Int = load(context).size

    private fun persist(context: Context, books: List<Book>) {
        val raw = books.joinToString(RECORD_SEPARATOR) { book ->
            listOf(
                book.title,
                book.author,
                book.edition,
                book.courseCode,
                book.condition,
                book.price,
                book.sellerName,
                book.description,
                book.campus
            ).joinToString(FIELD_SEPARATOR) { encode(it) }
        }

        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LISTINGS, raw)
            .apply()
    }

    private fun encode(value: String): String {
        return value
            .replace("\\", "\\\\")
            .replace(FIELD_SEPARATOR, "\\p")
            .replace(RECORD_SEPARATOR, "\\n")
    }

    private fun decode(value: String): String {
        val output = StringBuilder()
        var escaping = false
        value.forEach { char ->
            if (escaping) {
                output.append(
                    when (char) {
                        'p' -> FIELD_SEPARATOR
                        'n' -> RECORD_SEPARATOR
                        '\\' -> "\\"
                        else -> char.toString()
                    }
                )
                escaping = false
            } else if (char == '\\') {
                escaping = true
            } else {
                output.append(char)
            }
        }
        if (escaping) output.append('\\')
        return output.toString()
    }
}
