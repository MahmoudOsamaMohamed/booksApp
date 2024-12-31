package com.example.booksapp.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.common.Resource
import com.example.booksapp.data.model.Book
import com.example.booksapp.domain.usecase.GetBookDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookDetailsUseCase: GetBookDetailsUseCase
): ViewModel(){
    private val _book = MutableStateFlow<Book?>(null)
    val book = _book.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    fun getBookDetails(id: String) {
        getBookDetailsUseCase.invoke(id).onEach {
            when(it){
                is Resource.Error -> {
                    _error.value = it.message
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _book.value = it.data
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}