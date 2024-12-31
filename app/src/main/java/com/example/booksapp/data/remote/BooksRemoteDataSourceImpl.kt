package com.example.booksapp.data.remote

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BooksResponse
import javax.inject.Inject

class BooksRemoteDataSourceImpl@Inject constructor(
    private val booksApiService: BooksApiService
): BooksRemoteDataSource {
    override suspend fun getRecentBooks(): BooksResponse {
        return booksApiService.getRecentBooks()
    }

    override suspend fun getBookDetails(id: String): Book {
        return booksApiService.getBookDetails(id)
    }

}