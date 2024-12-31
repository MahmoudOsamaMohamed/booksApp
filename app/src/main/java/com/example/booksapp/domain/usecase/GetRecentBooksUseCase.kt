package com.example.booksapp.domain.usecase

import android.util.Log
import com.example.booksapp.common.Resource
import com.example.booksapp.data.model.Book
import com.example.booksapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetRecentBooksUseCase @Inject constructor(
    private val repo: BooksRepository
) {
    operator fun invoke(): Flow<Resource<List<Book>, String>> = flow {
        emit(Resource.Loading())
        try {
            val response = repo.getRecentBooks()
            if (response.status == "ok") {
                emit(Resource.Success(response.books))

            } else {
                emit(Resource.Error(message = "check your internet connection"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}