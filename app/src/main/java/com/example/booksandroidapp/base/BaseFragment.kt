package com.example.booksandroidapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
):Fragment() {

    private var _binding:VB?=null
    val binding:VB get() = _binding as VB

    protected abstract val viewModel:VM
    protected abstract fun observeEvents()
    protected abstract fun onViewCreateFinish()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=bindingInflater.invoke(layoutInflater)

        if (_binding==null){
            throw IllegalArgumentException("Binding null")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreateFinish()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}