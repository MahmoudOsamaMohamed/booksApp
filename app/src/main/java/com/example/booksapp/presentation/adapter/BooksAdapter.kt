package com.example.booksapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.booksapp.data.model.Book
import com.example.booksapp.databinding.ItemBookBinding

class BooksAdapter(
    private val onItemClick: (String) -> Unit // Callback for item click
) : ListAdapter<Book, BooksAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.textViewTitle.text = book.title
            binding.textViewAuthor.text = book.authors
            binding.imageViewThumbnail.load(book.image)

            // Handle item click
            binding.root.setOnClickListener {
                onItemClick(book.id)
            }
        }
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
}

