package com.example.booksapp.data.remote

import com.example.booksapp.common.ConstantsEndPoints
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET(ConstantsEndPoints.RECENT_BOOKS)
    suspend fun getRecentBooks(): BooksResponse

    @GET(ConstantsEndPoints.BOOK_DETAILS+"{id}")
    suspend fun getBookDetails(@Path("id") id: String): Book

}