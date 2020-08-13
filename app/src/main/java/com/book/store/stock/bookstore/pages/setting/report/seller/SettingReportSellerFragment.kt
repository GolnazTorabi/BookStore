package com.book.store.stock.bookstore.pages.setting.report.seller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingReportSellerFragmentBinding
import com.book.store.stock.bookstore.pages.dash_board.DashBoardAdapter
import com.book.store.stock.bookstore.utility.LoadMoreListener

class SettingReportSellerFragment : DialogFragment(),LoadMoreListener {

    companion object {
        fun newInstance() =
            SettingReportSellerFragment()
    }

    private lateinit var viewModel: SettingReportSellerViewModel
    private lateinit var binding: SettingReportSellerFragmentBinding
    private lateinit var notificationAdapter: SettingReportSellerAdapter

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

    private fun initSellerAdapter() {
        notificationAdapter = SettingReportSellerAdapter(listOf<String>("", "") as ArrayList<String>, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = notificationAdapter
    }

    override fun onLoadMore() {
        //todo api call
    }


}