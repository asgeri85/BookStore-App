package com.example.booksandroidapp.ui.basket

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentBasketBinding
import com.example.booksandroidapp.model.BasketResponseModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding, BasketViewModel>(
    FragmentBasketBinding::inflate
) {

    private val basketBookAdapter: BasketBookAdapter = BasketBookAdapter()
    override val viewModel: BasketViewModel by viewModels<BasketViewModel>()
    private lateinit var price: String
    private lateinit var count: String


    override fun onViewCreateFinish() {
        setRecycler()
        with(binding) {
            buttonBasketPay.setOnClickListener {
                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToCheckoutFragment(
                    BasketResponseModel(price, count)))
            }
            buttonClearBasket.setOnClickListener {
                viewModel.clearBasket()
            }
            buttonBasketStartOrder.setOnClickListener {
                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToHomeFragment())
            }
        }

        basketBookAdapter.onClickMinus = {
            viewModel.decrease(it)
        }

        basketBookAdapter.onClickPlus = {
            viewModel.increase(it)
        }
    }

    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                basketData.observe(viewLifecycleOwner) {
                    it?.let {
                        if (it.isNotEmpty()) {
                            basketBookAdapter.updateList(it)
                            constraintLayout4.visibility = View.VISIBLE
                            constraintLayout6.visibility = View.VISIBLE
                            constraitEmpty.visibility = View.GONE
                            count = it.size.toString()
                        } else {
                            constraintLayout4.visibility = View.INVISIBLE
                            constraintLayout6.visibility = View.INVISIBLE
                            constraitEmpty.visibility = View.VISIBLE
                        }
                    }
                }

                totalBasket.observe(viewLifecycleOwner) {
                    textViewBasket.text = "$it $"
                    price = it.toString()
                }

                loading.observe(viewLifecycleOwner) {
                    if (it) {
                        loadingBasket.visibility = View.VISIBLE
                    } else {
                        loadingBasket.visibility = View.GONE
                    }
                }

                crudBasket.observe(viewLifecycleOwner) {
                    it?.let {
                        if (it.status == 1) {
                            FancyToast.makeText(requireContext(),
                                it.message,
                                FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS,
                                false).show()
                            viewModel.getBasket()
                        } else {
                            FancyToast.makeText(requireContext(),
                                it.message,
                                FancyToast.LENGTH_SHORT,
                                FancyToast.WARNING,
                                false).show()
                        }
                    }
                }

                error.observe(viewLifecycleOwner) {
                    it?.let {
                        FancyToast.makeText(requireContext(),
                            it,
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false).show()
                    }
                }

            }
        }
    }

    private fun setRecycler() {
        with(binding) {
            with(rvBasket) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = basketBookAdapter
            }
        }
    }

}