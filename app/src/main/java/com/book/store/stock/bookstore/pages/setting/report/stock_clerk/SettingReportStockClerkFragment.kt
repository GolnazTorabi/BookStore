package com.book.store.stock.bookstore.pages.setting.report.stock_clerk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingReportStockClerkFragmentBinding
import javax.inject.Inject

class SettingReportStockClerkFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingReportStockClerkFragment()
    }

    private lateinit var viewModel: SettingReportStockClerkViewModel
    private lateinit var binding: SettingReportStockClerkFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_report_stock_clerk_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingReportStockClerkViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}