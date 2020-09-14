package com.book.store.stock.bookstore.pages.setting.report.stock_clerk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.Item
import com.book.store.stock.bookstore.databinding.SettingReportStockClerkFragmentBinding
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingReportStockClerkFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SettingReportStockClerkFragment()
    }

    private lateinit var viewModel: SettingReportStockClerkViewModel
    private lateinit var binding: SettingReportStockClerkFragmentBinding

    private lateinit var adapter: SettingNewBookAdapter
    private var bookList = ArrayList<Item>()


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
        binding.back.setOnClickListener { activity?.onBackPressed() }
        initAdapter()
        getNewBookData()
        goToAddNewBook()
        submitData()
    }

    private fun submitData() {
        binding.submit.setOnClickListener {
            //Todo
            if (binding.buyBook.isChecked) {
                //todo add to database
            } else if (binding.marjo.isChecked) {
                //todo marjo kardan
            }
        }
    }

    private fun goToAddNewBook() {
        binding.newReport.setOnClickListener { findNavController().navigate(R.id.action_settingNewBookFragment_to_settingAddNewBookFragment) }
    }

    private fun initAdapter() {
        adapter = SettingNewBookAdapter(bookList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
    }

    private fun getNewBookData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("book")?.observe(viewLifecycleOwner, Observer {
            bookList.add(Item(it[0], it[3].toInt()))
            adapter.notifyDataSetChanged()
        })
    }

}