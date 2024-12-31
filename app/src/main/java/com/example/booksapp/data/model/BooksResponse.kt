package com.example.booksapp.data.model

data class BooksResponse(
    val status: String,
    val total: Int,
    val books: List<Book>
)
