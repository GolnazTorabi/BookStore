package com.book.store.stock.bookstore.pages.setting.request.seller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingRequestSellerFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingRequestSellerFragment()
    }

    private lateinit var viewModel: SettingRequestSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_request_seller_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingRequestSellerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}