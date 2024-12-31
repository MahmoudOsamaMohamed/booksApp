package com.example.booksapp.di

import com.example.booksapp.common.ConstantsEndPoints
import com.example.booksapp.data.remote.BooksApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsEndPoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleBooksApi(retrofit: Retrofit): BooksApiService {
        return retrofit.create(BooksApiService::class.java)
    }
}
