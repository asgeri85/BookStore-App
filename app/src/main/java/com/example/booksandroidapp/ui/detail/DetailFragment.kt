package com.example.booksandroidapp.ui.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentDetailBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    private val args:DetailFragmentArgs by navArgs()
    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun onViewCreateFinish() {
        args.bookItem?.let {
            binding.bookDetail=it
        }

        with(binding){
            buttonDetailNavigation.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddBasketDetail.setOnClickListener {
                args.bookItem?.let {
                    viewModel.addBasket(it,"1")
                }
            }
            buttonDetailFavorite.setOnClickListener {
                args.bookItem?.let {
                    viewModel.addFavorites(it)
                }
            }
        }

    }

    override fun observeEvents() {
        with(binding){
            with(viewModel){
                bagResponse.observe(viewLifecycleOwner){
                    it?.let {
                        if (it.status==1){
                            FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                        }else{
                            FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show()
                        }
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        buttonAddBasketDetail.isClickable=false
                        loadingDetail.visibility=View.VISIBLE
                    }else{
                        buttonAddBasketDetail.isClickable=true
                        loadingDetail.visibility=View.GONE
                    }
                }

                error.observe(viewLifecycleOwner){
                    it?.let {
                        FancyToast.makeText(requireContext(),it,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
                    }
                }

                favoriteResponse.observe(viewLifecycleOwner){
                    it?.let {
                        if (it){
                            FancyToast.makeText(requireContext(),"Book add favorites",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                        }
                    }
                }
            }
        }
    }

}