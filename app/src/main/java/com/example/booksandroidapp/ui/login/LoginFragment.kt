package com.example.booksandroidapp.ui.login

import android.content.Context
import android.os.PerformanceHintManager
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentLoginBinding
import com.example.booksandroidapp.utils.Extensions.isNullOrEmpty
import com.google.android.material.textfield.TextInputEditText
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreateFinish() {
        binding.buttonLoginCreate.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    override fun observeEvents() {
        with(binding){
            with(viewModel){
                resultAuth.observe(viewLifecycleOwner){
                    it?.let {
                        if (it.isSuccess){
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                            FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()
                            setSession()
                        }else{
                            FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
                        }
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        animationView.visibility=View.VISIBLE
                        buttonLogin.isClickable=false
                    }else{
                        animationView.visibility=View.GONE
                        buttonLogin.isClickable=true
                    }
                }
            }
        }
    }

    private fun login(){
        with(binding){
            if (checkInfo(editLoginMail,editLoginPass)){
                val email=editLoginMail.text.toString().trim()
                val pass =editLoginPass.text.toString().trim()
                viewModel.signIn(email,pass)
            }
        }
    }

    private fun checkInfo(
        email: TextInputEditText,
        password: TextInputEditText,
    ): Boolean {
        val chekInfo = when {
            email.isNullOrEmpty("Cannot be empty").not() -> false
            password.isNullOrEmpty("Cannot be empty").not() -> false
            else -> true
        }

        return chekInfo
    }

    private fun setSession(){
        val sp by lazy {
            requireContext().getSharedPreferences("SessionSP",Context.MODE_PRIVATE)
        }
        sp.edit().putBoolean("session",true).apply()
    }

}