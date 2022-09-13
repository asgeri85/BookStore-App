package com.example.booksandroidapp.ui.register

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentRegisterBinding
import com.example.booksandroidapp.ui.login.LoginViewModel
import com.example.booksandroidapp.utils.Extensions.isNullOrEmpty
import com.google.android.material.textfield.TextInputEditText
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, LoginViewModel>(
    FragmentRegisterBinding::inflate
) {
    override val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreateFinish() {
        binding.buttonRegister.setOnClickListener {
            registerAccount()
        }
    }

    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                resultAuth.observe(viewLifecycleOwner) {
                    it?.let {
                        if (it.isSuccess) {
                            findNavController().popBackStack()
                            FancyToast.makeText(requireContext(),
                                it.message,
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,
                                false).show()
                        } else {
                            FancyToast.makeText(requireContext(),
                                it.message,
                                FancyToast.LENGTH_LONG,
                                FancyToast.ERROR,
                                false).show()
                        }
                    }
                }

                loading.observe(viewLifecycleOwner) {
                    if (it) {
                        animationView.visibility = View.VISIBLE
                        buttonRegister.isClickable = false
                    } else {
                        animationView.visibility = View.GONE
                        buttonRegister.isClickable = true
                    }
                }
            }
        }
    }

    private fun registerAccount() {
        with(binding) {
            if (checkInfo(editRegisterMail, editRegisterPassword, editRegisterRePass)){
                val email = editRegisterMail.text.toString().trim()
                val password = editRegisterPassword.text.toString().trim()
                val rePassword=editRegisterRePass.text.toString().trim()
                if (password == rePassword){
                    viewModel.signUp(email, password)
                }else{
                    FancyToast.makeText(requireContext(),
                        "Passwords are not the same",
                        FancyToast.LENGTH_LONG,
                        FancyToast.WARNING,
                        false).show()
                }

            }
        }
    }

    private fun checkInfo(
        email: TextInputEditText,
        password: TextInputEditText,
        rePassword: TextInputEditText,
    ): Boolean {
        val chekInfo = when {
            email.isNullOrEmpty("Cannot be empty").not() -> false
            password.isNullOrEmpty("Cannot be empty").not() -> false
            rePassword.isNullOrEmpty("Cannot be empty").not() -> false
            else -> true
        }

        return chekInfo
    }

}