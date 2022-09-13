package com.example.booksandroidapp.ui.basket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksandroidapp.databinding.CardBasketBinding
import com.example.booksandroidapp.model.BookResponseItem

class BasketBookAdapter : RecyclerView.Adapter<BasketBookAdapter.BookViewHolder>() {

    private val basketBookList = arrayListOf<BookResponseItem>()
    var onClickPlus: (Double) -> Unit = {}
    var onClickMinus: (Double) -> Unit = {}

    inner class BookViewHolder(private val cardBasketBinding: CardBasketBinding) :
        RecyclerView.ViewHolder(cardBasketBinding.root) {
        fun bind(
            bookResponseItem: BookResponseItem, ) {
            cardBasketBinding.book = bookResponseItem
            cardBasketBinding.executePendingBindings()
            var itemCount=1
            cardBasketBinding.count=itemCount.toString()

            cardBasketBinding.imageButtonMinus.setOnClickListener {
                if (itemCount>1){
                    itemCount-=1
                    cardBasketBinding.count=itemCount.toString()
                    onClickMinus(bookResponseItem.price!!.toDouble())
                }

            }

            cardBasketBinding.imageButtonPlus.setOnClickListener {
                itemCount+=1
                cardBasketBinding.count=itemCount.toString()
                onClickPlus(bookResponseItem.price!!.toDouble())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layout = CardBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = basketBookList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return basketBookList.size
    }

    fun updateList(list: List<BookResponseItem>) {
        basketBookList.clear()
        basketBookList.addAll(list)
        notifyDataSetChanged()
    }

}