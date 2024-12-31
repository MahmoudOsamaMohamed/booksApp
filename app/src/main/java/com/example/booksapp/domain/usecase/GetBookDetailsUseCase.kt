package com.example.booksapp.domain.usecase

import com.example.booksapp.common.Resource
import com.example.booksapp.data.model.Book
import com.example.booksapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val repo: BooksRepository
) {
    operator fun invoke(
        id: String
    ): Flow<Resource<Book, String>> = flow {
        emit(Resource.Loading())
        try {
            val response = repo.getBookDetails(id = id)
            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}