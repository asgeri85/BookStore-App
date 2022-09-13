package com.example.booksandroidapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksandroidapp.databinding.CardBookBinding
import com.example.booksandroidapp.model.BookResponseItem

class HomeBookAdapter : RecyclerView.Adapter<HomeBookAdapter.BookViewHolder>() {

    private val bookList = arrayListOf<BookResponseItem>()
    var onClick: (BookResponseItem) -> Unit = {}

    class BookViewHolder(private val cardBookBinding: CardBookBinding) :
        RecyclerView.ViewHolder(cardBookBinding.root) {
        fun find(bookResponseItem: BookResponseItem,onClick: (BookResponseItem) -> Unit = {}) {
            cardBookBinding.book = bookResponseItem
            cardBookBinding.executePendingBindings()

            cardBookBinding.cardBook.setOnClickListener {
                onClick(bookResponseItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = CardBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.find(book,onClick)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateList(list: List<BookResponseItem>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }
}