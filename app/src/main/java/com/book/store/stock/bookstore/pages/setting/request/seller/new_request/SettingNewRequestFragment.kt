package com.book.store.stock.bookstore.pages.setting.request.seller.new_request

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
import com.book.store.stock.bookstore.databinding.SettingNewRequestFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingNewRequestFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SettingNewRequestFragment()
    }

    private lateinit var viewModel: SettingNewRequestViewModel
    private lateinit var binding: SettingNewRequestFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_new_request_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingNewRequestViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkEditText()
        submitButtonClicked()
        binding.back.setOnClickListener { activity?.onBackPressed() }
    }

    private fun submitButtonClicked() {
        if (binding.submit.isEnabled) {
            binding.submit.setOnClickListener {
                val list = ArrayList<String>()
                list.add(0, binding.bookEdit.text.toString())
                list.add(0, binding.writerEdit.text.toString())
                list.add(0, binding.publisherEdit.text.toString())
                list.add(0, binding.countEdit.text.toString())
                findNavController().previousBackStackEntry?.savedStateHandle?.set("book_data", list)
                activity?.onBackPressed()
            }
        }
    }

    private fun checkEditText() {
        binding.bookEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 2) {
                binding.submit.isEnabled = false
            }
        }
        binding.writerEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 10) {
                binding.submit.isEnabled = false
            }
        }
        binding.publisherEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 1) {
                binding.submit.isEnabled = false
            }
        }
        binding.countEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 0 && it.toString() != "0") {
                binding.submit.isEnabled = false
            }
        }

        binding.countEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.countEditLayout.isErrorEnabled = false
            } else {
                if (binding.countEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.countEditLayout.isErrorEnabled = true
                    binding.countEditLayout.error = "رقم وارد شده باید بزرگ تر از ۰ باشد"
                }
            }
        }
        binding.publisherEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.publisherEditLayout.isErrorEnabled = false
            } else {
                if (binding.publisherEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.publisherEditLayout.isErrorEnabled = true
                    binding.publisherEditLayout.error = "نام انتشارات  را کامل وارد کنید"
                }
            }
        }
        binding.writerEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.writerEditLayout.isErrorEnabled = false
            } else {
                if (binding.writerEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.writerEditLayout.isErrorEnabled = true
                    binding.writerEditLayout.error = "نام نویسنده را کامل وارد کنید"
                }
            }
        }
        binding.bookEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.bookEditLayout.isErrorEnabled = false
            } else {
                if (binding.bookEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.bookEditLayout.isErrorEnabled = true
                    binding.bookEditLayout.error = "نام کتاب را کامل وارد کنید"
                }
            }
        }

    }

}