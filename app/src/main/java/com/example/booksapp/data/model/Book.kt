package com.example.booksapp.data.model

data class Book(
    val id: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val image: String,
    val publisher : String?,
    val description: String?,
    val pages: String?,
    val year: String?,

)
