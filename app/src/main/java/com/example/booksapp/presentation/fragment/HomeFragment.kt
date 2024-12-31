package com.example.booksapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.presentation.adapter.BooksAdapter
import com.example.booksapp.presentation.viewmodel.RecentBooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: RecentBooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        // Setup RecyclerView
        setupRecyclerView()
        // Observe ViewModel
        observeSearchResults()
        // Search functionality
        observeSearchEditText()
    }
    private fun observeSearchEditText() {
        binding.searchEditText.addTextChangedListener { editable ->
            val query = editable.toString()
            viewModel.searchOnBooks(query)
        }
    }
    private fun setupRecyclerView() {
        adapter = BooksAdapter { bookId ->
            val action = HomeFragmentDirections.actionHomeFragmentToBookDetailsFragment(bookId)
            findNavController().navigate(action)
        }
        binding.recyclerViewBooks.adapter = adapter
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun observeSearchResults() {
        viewModel.searchResults.onEach { books->
            adapter.submitList(books)
        }.launchIn(lifecycleScope)
        viewModel.isLoading.onEach { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }.launchIn(lifecycleScope)
        viewModel.error.onEach { error ->
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)


    }

}
