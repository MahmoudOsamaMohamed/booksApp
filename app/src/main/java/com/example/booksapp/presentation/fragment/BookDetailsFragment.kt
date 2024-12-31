package com.example.booksapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentBookDetailsBinding
import com.example.booksapp.presentation.viewmodel.BookDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {

    private val viewModel: BookDetailsViewModel by viewModels()
    private lateinit var binding: FragmentBookDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookDetailsBinding.bind(view)

        val args: BookDetailsFragmentArgs by navArgs()
        viewModel.getBookDetails(args.bookId)

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        observeBookDetails()
        observeLoading()
        observeError()

    }
    private fun observeBookDetails(){
        viewModel.book.onEach { book ->
            book?.let {
                binding.textViewTitle.text = it.title
                binding.textViewAuthor.text = it.authors
                binding.textViewPublishedDate.text = it.year
                binding.textViewDescription.text = it.description
                binding.imageViewThumbnail.load(it.image)
            }
        }.launchIn(lifecycleScope)
    }
    private fun observeLoading(){
        viewModel.isLoading.onEach {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }.launchIn(lifecycleScope)
    }
    private fun observeError(){
        viewModel.error.onEach { error ->
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)
    }
}
