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
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.databinding.DashBoardFragmentBinding
import com.book.store.stock.bookstore.pages.MainActivity
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

    private var bookList = ResponseBookListSeller()
    private var notification = ArrayList<String>()

    private var isSeller: Boolean? = false
    private var filter: String? = null

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
        (requireActivity() as MainActivity).setStatusBarColor(R.color.colorPrimaryDark)
        findNavController().addOnDestinationChangedListener(requireActivity() as MainActivity)
        checkIsSeller()
        observeFilterData()
        openFilterPage()
    }

    private fun openFilterPage() {
        binding.filter.setOnClickListener { findNavController().navigate(R.id.action_dashBoardFragment_to_dashBoardFilterFragment) }
    }

    private fun observeFilterData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("mostSale")?.observe(viewLifecycleOwner, Observer {
            when (it) {
                "mostSale" -> {
                    filter = "mostSale"
                    getBookData(filter ?: "")
                }
                "news" -> {
                    filter = "news"
                    getBookData(filter ?: "")
                }
                "bookName" -> {
                    filter = "bookName"
                    getBookData(filter ?: "")
                }
                "publisher" -> {
                    filter = "publisher"
                    getBookData(filter ?: "")
                }
            }
        })
    }

    private fun getBookData(data: String) {

        viewModel.requestGetAllBook(data)
        observeBookData()
    }

    private fun observeBookData() {
        viewModel.bookStatus.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    DashBoardViewModel.DashboardStatus.ShowLoading -> { /*todo show loading*/
                    }
                    DashBoardViewModel.DashboardStatus.HideLoading -> { /*todo hide loading*/
                    }
                    DashBoardViewModel.DashboardStatus.Fail -> getBookData("")

                }
            }
        })
        viewModel.books.observe(viewLifecycleOwner, Observer {
            bookList.clear()
            bookList.addAll(it)
            bookAdapter.notifyDataSetChanged()
        })
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
                        binding.filter.setOnClickListener { findNavController().navigate(R.id.action_dashBoardFragment_to_dashBoardFilterFragment) }
                        isSeller = true
                        initSellerAdapter()
                        getBookData("")
                    }
                    DashBoardViewModel.SellerStock.Stock -> {
                        isSeller = false
                        initStockClerkAdapter()
                        getNotifications()
                    }
                }
            }
        })
    }

    private fun getNotifications() {
        //todo get stock notifications
    }

    private fun initSellerAdapter() {
        bookAdapter = DashBoardAdapter(bookList, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = bookAdapter
    }

    private fun initStockClerkAdapter() {
        notificationAdapter = DashBoardNotifAdapter(notification, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = bookAdapter
    }

    override fun onLoadMore() {
        /* when (isSeller) {
             true -> {
                 when (filter?.isBlank()) {
                     true -> {*//*with no filter api call*//*
                    }
                    else -> {
                    }
                }
            }
            else -> {
            }
        }*/
    }
}