package com.example.booksandroidapp.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booksandroidapp.R
import com.example.booksandroidapp.databinding.FragmentBottomPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomPaymentFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomPaymentBinding? = null
    private val binding get() = _binding!!
    private val args: BottomPaymentFragmentArgs by navArgs()
    private val viewModel by viewModels<CheckoutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomPaymentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        with(binding) {
            textViewProducts.text = "Products: ${args.basketModel.count}"
            textViewPrice.text = "${args.basketModel.price} $"
            button8.setOnClickListener {
                viewModel.completeOrder()
            }
        }
    }

    private fun observeEvents(){
        with(binding){
            with(viewModel){
                crudCheckout.observe(viewLifecycleOwner){
                    it?.let {
                        if(it.status==1){
                            FancyToast.makeText(requireContext(),"Your order has been successfully created",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                            findNavController().popBackStack(R.id.homeFragment,false)
                        }else{
                            FancyToast.makeText(requireContext(),"An unknown error occurred",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}