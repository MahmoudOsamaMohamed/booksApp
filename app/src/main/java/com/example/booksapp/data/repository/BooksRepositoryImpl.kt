package com.example.booksapp.data.repository

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BooksResponse
import com.example.booksapp.data.remote.BooksRemoteDataSource
import com.example.booksapp.domain.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val remoteDataSource: BooksRemoteDataSource
): BooksRepository{
    override suspend fun getRecentBooks(): BooksResponse {
        return remoteDataSource.getRecentBooks()
    }

    override suspend fun getBookDetails(id: String): Book {
        return remoteDataSource.getBookDetails(id)
    }

}