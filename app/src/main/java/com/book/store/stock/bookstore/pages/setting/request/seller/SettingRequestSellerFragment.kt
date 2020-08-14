package com.book.store.stock.bookstore.pages.setting.request.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingRequestSellerFragmentBinding
import javax.inject.Inject

class SettingRequestSellerFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingRequestSellerFragment()
    }

    private lateinit var viewModel: SettingRequestSellerViewModel
    private lateinit var binding: SettingRequestSellerFragmentBinding

    private var bookList = ArrayList<String>()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_request_seller_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingRequestSellerViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    fun getNewBookData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("book_data")?.observe(viewLifecycleOwner, Observer {
            bookList.addAll(it)

        })
    }

}