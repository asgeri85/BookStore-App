package com.example.booksandroidapp.ui.splash

import android.content.Context
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: SplashViewModel by lazy { SplashViewModel() }

    override fun observeEvents() {
    }

    override fun onViewCreateFinish() {
        if (getSession()) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(2500)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }else{
            binding.button.visibility=View.VISIBLE
            binding.button.setOnClickListener {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }

    }

    private fun getSession(): Boolean {
        val sp by lazy {
            requireContext().getSharedPreferences("SessionSP", Context.MODE_PRIVATE)
        }

        return sp.getBoolean("session", false)
    }
}