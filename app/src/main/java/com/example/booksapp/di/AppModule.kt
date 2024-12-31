package com.example.booksapp.di

import com.example.booksapp.data.remote.BooksApiService
import com.example.booksapp.data.remote.BooksRemoteDataSource
import com.example.booksapp.data.remote.BooksRemoteDataSourceImpl
import com.example.booksapp.data.repository.BooksRepositoryImpl
import com.example.booksapp.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesBooksRemoteDataSource(
        apiService: BooksApiService
    ): BooksRemoteDataSource {
        return BooksRemoteDataSourceImpl(
            apiService
        )
    }
    @Provides
    @Singleton
    fun provideBooksRepository(
        remoteDataSource: BooksRemoteDataSource
    ): BooksRepository {
        return BooksRepositoryImpl(
            remoteDataSource
        )
    }
}