package com.example.booksandroidapp.ui.profile

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentProfileBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate
) {
    override val viewModel: ProfileViewModel by viewModels<ProfileViewModel>()

    override fun onViewCreateFinish() {
        binding.cardSignout.setOnClickListener {
            viewModel.signOut()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            resultAuth.observe(viewLifecycleOwner) {
                it?.let {
                    if (it.isSuccess) {
                        deleteSession()
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSplashFragment())
                    } else {
                        FancyToast.makeText(requireContext(),
                            it.message,
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false).show()
                    }
                }
            }
        }

    }

    private fun deleteSession() {
        val sp by lazy {
            requireContext().getSharedPreferences("SessionSP", Context.MODE_PRIVATE)
        }

        sp.edit().remove("session").apply()
    }

}