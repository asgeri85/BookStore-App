package com.example.booksandroidapp.ui.checkout

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : BaseFragment<FragmentCheckoutBinding, CheckoutViewModel>(
    FragmentCheckoutBinding::inflate
) {
    override val viewModel: CheckoutViewModel by viewModels<CheckoutViewModel>()
    private val args: CheckoutFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        Log.e("basket",args.basketModel.toString())
        args.basketModel?.let { basketModel ->
            binding.textViewCheckoutPrice.text = "${basketModel.price} $"
            binding.buttonCheckout.setOnClickListener {
                findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToBottomPaymentFragment(
                    basketModel))
            }
        }
    }

    override fun observeEvents() {}


}