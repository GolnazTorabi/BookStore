package com.book.store.stock.bookstore.pages.setting.report.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingReportSellerFragmentBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingReportSellerFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SettingReportSellerFragment()
    }

    private lateinit var viewModel: SettingReportSellerViewModel
    private lateinit var binding: SettingReportSellerFragmentBinding
    private lateinit var notificationAdapter: SettingReportSellerAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_report_seller_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingReportSellerViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSellerAdapter()
    }

    private fun initSellerAdapter() {
        notificationAdapter = SettingReportSellerAdapter(listOf<String>("", "") as ArrayList<String>)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = notificationAdapter
    }



}