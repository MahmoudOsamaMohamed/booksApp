package com.example.booksapp.data.remote

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BooksResponse

interface BooksRemoteDataSource {
    suspend fun getRecentBooks(): BooksResponse
    suspend fun getBookDetails(id: String): Book
}