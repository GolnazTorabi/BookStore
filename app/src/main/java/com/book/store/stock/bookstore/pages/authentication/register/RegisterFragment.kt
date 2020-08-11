package com.book.store.stock.bookstore.pages.authentication.register

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.RegisterFragmentBinding
import com.book.store.stock.bookstore.utility.Fonts
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RegisterFragment : DaggerFragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding
    private var isShowPass = false
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        checkEditTexts()
        binding.back.setOnClickListener { activity?.onBackPressed() }
        registerUser()
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


    private fun registerUser() {
        if(binding.registerButton.isEnabled){
            binding.registerButton.setOnClickListener {
                //todo api call
            }
        }
    }

    private fun checkEditTexts() {
        binding.codeEdit.doAfterTextChanged {
            binding.registerButton.isEnabled = it?.length == 4
        }
        binding.usernameEdit.doAfterTextChanged {
            binding.registerButton.isEnabled = it?.length?:0 >= 5
        }
        binding.nameEdit.doAfterTextChanged {
            binding.registerButton.isEnabled = it?.length?:0 >= 4
        }
        binding.familyEdit.doAfterTextChanged {
            binding.registerButton.isEnabled = it?.length?:0 >= 4
        }
        binding.emailEdit.doAfterTextChanged {
            binding.registerButton.isEnabled =
                android.util.Patterns.EMAIL_ADDRESS.matcher(it.toString()).matches();
        }
        binding.passwordEdit.doAfterTextChanged {
            binding.registerButton.isEnabled = it?.length?:0 >= 8
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
                binding.usernameEditLayout.isErrorEnabled = false
            } else {
                if (binding.usernameEdit.length() < 5) {
                    binding.usernameEditLayout.isErrorEnabled = true
                    binding.usernameEditLayout.error = "نام کاربری خود را کامل وارد کنید"
                }
            }
        }
        binding.nameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.nameEditLayout.isErrorEnabled = false
            } else {
                if (binding.nameEdit.length() < 3) {
                    binding.nameEditLayout.isErrorEnabled = true
                    binding.nameEditLayout.error = "نام  خود را کامل وارد کنید"
                }
            }
        }
        binding.familyEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.familyEditLayout.isErrorEnabled = false
            } else {
                if (binding.familyEdit.length() < 3) {
                    binding.familyEditLayout.isErrorEnabled = true
                    binding.familyEditLayout.error = "نام خانوادگی خود را کامل وارد کنید"
                }
            }
        }
        binding.emailEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.emailEditLayout.isErrorEnabled = false
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailEdit.text.toString()).matches()) {
                    binding.emailEditLayout.isErrorEnabled = true
                    binding.emailEditLayout.error = "ایمیل خود را درست و کامل وارد کنید"
                }
            }
        }
        binding.passwordEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.passwordEditLayout.isErrorEnabled = false
            } else {
                if (binding.passwordEdit.length() < 8) {
                    binding.passwordEditLayout.isErrorEnabled = true
                    binding.passwordEditLayout.error = "رمز عبور خود را کامل وارد کنید"
                }
            }
        }


    }

}