package com.book.store.stock.bookstore.pages.dash_board

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
import com.book.store.stock.bookstore.databinding.DashBoardFragmentBinding
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import com.book.store.stock.bookstore.utility.LoadMoreListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DashBoardFragment : DaggerFragment(), LoadMoreListener {

    private lateinit var viewModel: DashBoardViewModel
    private lateinit var binding: DashBoardFragmentBinding

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var bookList = ArrayList<String>()
    private var notification = ArrayList<String>()

    private var isSeller: Boolean? = false

    private lateinit var bookAdapter: DashBoardAdapter
    private lateinit var notificationAdapter: DashBoardNotifAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dash_board_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(DashBoardViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIsSeller()
        binding.back.setOnClickListener { activity?.onBackPressed() }
        binding.filter.setOnClickListener { findNavController().navigate(R.id.action_dashBoardFragment_to_dashBoardFilterFragment) }
        observeFilterData()
    }

    private fun observeFilterData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("mostSale")?.observe(viewLifecycleOwner, Observer {
            when (it) {
                "mostSale" -> getBookData("mostSale")
                "news" -> getBookData("news")
                "bookName" -> getBookData("bookName")
                "publisher" -> getBookData("publisher")
            }
        })
    }

    private fun getBookData(data: String) {
        if (data.isBlank()) {
            //first data
        } else {

        }
    }

    private fun checkIsSeller() {
        viewModel.isSeller()
        observeIsSeller()
    }

    private fun observeIsSeller() {
        viewModel.isSeller.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    DashBoardViewModel.SellerStock.Seller -> {
                        isSeller = true
                        initSellerAdapter()
                    }
                    DashBoardViewModel.SellerStock.Stock -> {
                        isSeller = false
                        initStockClerkAdapter()
                    }
                }
            }
        })
    }

    private fun initSellerAdapter() {
        bookAdapter = DashBoardAdapter(listOf<String>("", "") as ArrayList<String>, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = bookAdapter
    }

    private fun initStockClerkAdapter() {
        notificationAdapter = DashBoardNotifAdapter(listOf<String>("", "") as ArrayList<String>, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = bookAdapter
    }

    override fun onLoadMore() {
        when (isSeller) {
            true -> {
            }
            else -> {
            }
        }
    }
}