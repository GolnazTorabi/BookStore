package com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingAddNewBookFragmentBinding
import dagger.android.support.DaggerFragment
import saman.zamani.persiandate.PersianDate
import javax.inject.Inject


class SettingAddNewBookFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SettingAddNewBookFragment()
    }

    private lateinit var viewModel: SettingAddNewBookViewModel
    private lateinit var binding: SettingAddNewBookFragmentBinding

    private var date = PersianDate()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_add_new_book_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SettingAddNewBookViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBardCodeData()
        checkEditText()
        sendData()
        binding.back.setOnClickListener { activity?.onBackPressed() }
    }

    private fun sendData() {
        binding.submit.setOnClickListener {
            val list = ArrayList<String>()
            list.add(0, binding.bookEdit.text.toString())
            list.add(0, binding.writerEdit.text.toString())
            list.add(0, binding.publisherEdit.text.toString())
            list.add(0, binding.countEdit.text.toString())
            findNavController().previousBackStackEntry?.savedStateHandle?.set("book", list)
            activity?.onBackPressed()
        }
    }

    private fun getBardCodeData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("scanner")?.observe(viewLifecycleOwner, Observer {
            binding.barcode.text = it[0]
        })
    }

    private fun checkEditText() {
        binding.bookEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 2) {
                checkButton()
            }

        }
        binding.priceEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 3) {
                checkButton()
            }
        }
        binding.nobatChapEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 0) {
                checkButton()
            }
        }
        binding.yearEdit.doAfterTextChanged {
            if (it?.length ?: 0 == 4 && date.shYear != it.toString().toInt()) {
                checkButton()
            }
        }
        binding.writerEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 10) {
                checkButton()
            }
        }
        binding.publisherEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 1) {
                checkButton()
            }
        }
        binding.countEdit.doAfterTextChanged {
            if (it?.length ?: 0 > 0 && it.toString() != "0") {
                checkButton()
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
        binding.priceEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.priceEditLayout.isErrorEnabled = false
            } else {
                if (binding.priceEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.priceEditLayout.isErrorEnabled = true
                    binding.priceEditLayout.error = "قیمت را درست وارد کنید"
                }
            }
        }
        binding.nobatChapEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.nobatChapEditLayout.isErrorEnabled = false
            } else {
                if (binding.nobatChapEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.nobatChapEditLayout.isErrorEnabled = true
                    binding.nobatChapEditLayout.error = "رقم وارد شده باید بزرگ تر از ۰ باشد"
                }
            }
        }
        binding.yearEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.yearEditLayout.isErrorEnabled = false
            } else {
                if (binding.yearEdit.length() < 4) {
                    binding.submit.isEnabled = false
                    binding.yearEditLayout.isErrorEnabled = true
                    binding.yearEditLayout.error = "سال وارد شده درست نمیباشد"
                }
                if (binding.yearEdit.length() == 4 && date.shYear != binding.yearEdit.text.toString().toInt()) {
                    binding.submit.isEnabled = false
                    binding.yearEditLayout.isErrorEnabled = true
                    binding.yearEditLayout.error = "سال وارد شده درست نمیباشد"
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

    private fun checkButton() {
        binding.submit.isEnabled = (binding.bookEdit.length() > 2
                && binding.priceEdit.length() > 3
                && binding.nobatChapEdit.length() > 0
                && binding.yearEdit.length() == 4
                && date.shYear != binding.yearEdit.text.toString().toInt()
                && binding.writerEdit.length() > 10
                && binding.publisherEdit.length() > 1
                && binding.countEdit.length() > 0
                && binding.countEdit.text.toString() != "0")
    }


}