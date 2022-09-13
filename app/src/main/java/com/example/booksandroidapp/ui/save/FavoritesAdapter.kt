package com.example.booksandroidapp.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksandroidapp.databinding.CardSaveBinding
import com.example.booksandroidapp.model.BookResponseItem

class FavoritesAdapter :RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){

    private val favorites= arrayListOf<BookResponseItem>()
    var onClickDelete: (String) -> Unit = {}

    inner class FavoritesViewHolder(private val cardSaveBinding: CardSaveBinding):RecyclerView.ViewHolder(cardSaveBinding.root){
        fun bind(bookResponseItem: BookResponseItem){
            cardSaveBinding.book=bookResponseItem
            cardSaveBinding.executePendingBindings()

            cardSaveBinding.imageButton.setOnClickListener {
                onClickDelete(bookResponseItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layout=CardSaveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val book=favorites[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    fun updateList(list:List<BookResponseItem>){
        favorites.clear()
        favorites.addAll(list)
        notifyDataSetChanged()
    }
}