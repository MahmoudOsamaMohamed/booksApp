package com.example.booksapp.domain.repository

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BooksResponse

interface BooksRepository {
    suspend fun getRecentBooks(): BooksResponse
    suspend fun getBookDetails(id: String): Book
}