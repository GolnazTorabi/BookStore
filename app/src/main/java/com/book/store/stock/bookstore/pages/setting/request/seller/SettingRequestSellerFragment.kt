package com.book.store.stock.bookstore.pages.setting.request.seller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingRequestSellerFragmentBinding

class SettingRequestSellerFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingRequestSellerFragment()
    }

    private lateinit var viewModel: SettingRequestSellerViewModel
    private lateinit var binding: SettingRequestSellerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.setting_request_seller_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SettingRequestSellerViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}