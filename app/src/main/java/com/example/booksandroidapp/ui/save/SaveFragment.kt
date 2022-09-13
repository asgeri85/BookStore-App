package com.example.booksandroidapp.ui.save

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentSaveBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : BaseFragment<FragmentSaveBinding, SaveViewModel>(
    FragmentSaveBinding::inflate
) {
    override val viewModel: SaveViewModel by viewModels<SaveViewModel>()
    private val favoritesAdapter:FavoritesAdapter= FavoritesAdapter()

    override fun onViewCreateFinish() {
        setRecycler()
        favoritesAdapter.onClickDelete={
            viewModel.deleteBookFavorites(it)
        }
        binding.buttonClearFavorites.setOnClickListener {
            viewModel.deleteFavorites()
        }
    }

    override fun observeEvents() {
        with(viewModel){
            favoriteData.observe(viewLifecycleOwner){
                it?.let {
                    if (it.isNotEmpty()){
                        favoritesAdapter.updateList(it)
                        binding.rvFavorites.visibility=View.VISIBLE
                        binding.constraitEmpty.visibility=View.GONE
                    }else{
                        binding.rvFavorites.visibility=View.INVISIBLE
                        binding.constraitEmpty.visibility=View.VISIBLE
                    }
                }
            }

            error.observe(viewLifecycleOwner){
                it?.let {
                    FancyToast.makeText(requireContext(),it,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
                }
            }


        }
    }

    private fun setRecycler(){
        with(binding.rvFavorites){
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=favoritesAdapter
        }
    }

}