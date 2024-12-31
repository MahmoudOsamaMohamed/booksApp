package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.common.Resource
import com.example.booksapp.data.model.Book
import com.example.booksapp.domain.usecase.GetRecentBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class RecentBooksViewModel @Inject constructor(
    private val getRecentBooksUseCase: GetRecentBooksUseCase
) : ViewModel(){
    private val _recentBooks = MutableStateFlow<List<Book>>(emptyList())
    val recentBooks = _recentBooks.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    private val _searchResults = MutableStateFlow<List<Book>>(emptyList())
    val searchResults = _searchResults.asStateFlow()
    init {
        getRecentBooks()
    }
    private fun getRecentBooks(){
        getRecentBooksUseCase.invoke().onEach {
            when(it){
                is Resource.Error -> {
                    _error.value = it.message
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _recentBooks.value = it.data ?: emptyList()
                    _isLoading.value = false
                    _searchResults.value = it.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchOnBooks(query: String){
        _searchResults.value = _recentBooks.value.filter {
            it.title.contains(query, ignoreCase = true)  || it.authors.contains(query, ignoreCase = true)
        }
    }

}