package com.book.store.stock.bookstore.pages.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.LoginFragmentBinding
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        checkEditTexts()
        loginButtonClicked()
        forgetPassword()
        register()
    }

    private fun register() {
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun forgetPassword() {
        binding.forgetPass.setOnClickListener {
            //todo go to forget pass
        }
    }

    private fun loginButtonClicked() {
        if (binding.loginButton.isEnabled) {
            // api call
            binding.loginButton.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_dashboard_graph) }
        }
    }

    private fun checkEditTexts() {
        binding.codeEdit.doAfterTextChanged {
            binding.loginButton.isEnabled = it?.length != 4
        }
        binding.usernameEdit.doAfterTextChanged {
            binding.loginButton.isEnabled = it?.length != 5
        }
        binding.passwordEdit.doAfterTextChanged {
            binding.loginButton.isEnabled = it?.length != 8
        }

        binding.codeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.codeEditLayout.isErrorEnabled = false
            } else {
                if (binding.codeEdit.length() < 4) {
                    binding.codeEditLayout.isErrorEnabled = true
                    binding.codeEditLayout.error = "کد کاربری خود را کامل وارد کنید"
                }
            }
        }
        binding.usernameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.codeEditLayout.isErrorEnabled = false
            } else {
                if (binding.codeEdit.length() < 4) {
                    binding.codeEditLayout.isErrorEnabled = true
                    binding.codeEditLayout.error = "نام کاربری خود را کامل وارد کنید"
                }
            }
        }
        binding.passwordEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.codeEditLayout.isErrorEnabled = false
            } else {
                if (binding.codeEdit.length() < 8) {
                    binding.codeEditLayout.isErrorEnabled = true
                    binding.codeEditLayout.error = "پسورد خود را کامل وارد کنید"
                }
            }
        }
    }

}