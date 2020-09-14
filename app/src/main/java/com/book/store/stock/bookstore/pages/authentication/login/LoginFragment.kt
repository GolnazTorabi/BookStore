package com.book.store.stock.bookstore.pages.authentication.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.LoginFragmentBinding
import com.book.store.stock.bookstore.pages.MainActivity
import com.book.store.stock.bookstore.utility.Fonts
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var isShowPass = false

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
        eyeClick()
    }

    private fun eyeClick() {
        binding.eye.setOnClickListener {
            if (isShowPass) {
                binding.eye.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                binding.passwordEdit.typeface = Fonts.typefaceDefault(context)
                binding.passwordEdit.inputType =
                    InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                binding.passwordEdit.transformationMethod = PasswordTransformationMethod()
            } else {
                binding.eye.setImageResource(R.drawable.ic_baseline_visibility_24)
                binding.passwordEdit.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            }
            binding.passwordEdit.typeface = context?.let { it1 -> Fonts.typefaceBold(it1) }
            isShowPass = !isShowPass
        }

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
            binding.loginButton.setOnClickListener {
                viewModel.requestForToken(binding.usernameEdit.text.toString(), binding.passwordEdit.text.toString())
                observeRefresh()

            }
        }
    }

    private fun observeRefresh() {
        viewModel.getTokenStatus.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    LoginViewModel.LoginStatus.Success -> loginUser()
                    LoginViewModel.LoginStatus.NoInternet -> noInternetConnection()
                }
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun noInternetConnection() {
        //todo no internet
    }

    private fun loginUser() {
        viewModel.requestUserRole()
        observeIsSeller()
    }

    private fun observeIsSeller() {
        viewModel.seller.observe(viewLifecycleOwner, Observer {
            activity?.finish()
            startActivity(Intent(context, MainActivity::class.java))
        })
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
                    binding.loginButton.isEnabled = false
                    binding.codeEditLayout.isErrorEnabled = true
                    binding.codeEditLayout.error = "کد کاربری خود را کامل وارد کنید"
                }
            }
        }
        binding.usernameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.usernameEditLayout.isErrorEnabled = false
            } else {
                if (binding.usernameEdit.length() < 4) {
                    binding.loginButton.isEnabled = false
                    binding.usernameEditLayout.isErrorEnabled = true
                    binding.usernameEditLayout.error = "نام کاربری خود را کامل وارد کنید"
                }
            }
        }
        binding.passwordEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.passwordEditLayout.isErrorEnabled = false
            } else {
                if (binding.passwordEdit.length() < 8) {
                    binding.loginButton.isEnabled = false
                    binding.passwordEditLayout.isErrorEnabled = true
                    binding.passwordEditLayout.error = "پسورد خود را کامل وارد کنید"
                }
            }
        }
    }

}