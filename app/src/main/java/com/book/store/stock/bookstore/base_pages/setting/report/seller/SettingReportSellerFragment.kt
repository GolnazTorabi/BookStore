package com.book.store.stock.bookstore.base_pages.setting.report.seller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingReportSellerFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingReportSellerFragment()
    }

    private lateinit var viewModel: SettingReportSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_report_seller_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingReportSellerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}