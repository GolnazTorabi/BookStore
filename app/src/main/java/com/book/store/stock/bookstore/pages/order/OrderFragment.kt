package com.book.store.stock.bookstore.pages.order

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
import com.book.store.stock.bookstore.databinding.OrderFragmentBinding
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class OrderFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            OrderFragment()
    }

    private lateinit var viewModel: OrderViewModel
    private lateinit var binding: OrderFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var isSeller: Boolean? = false
    private var bookData = ArrayList<String>()
    private lateinit var adapter: OrderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(OrderViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.back.setOnClickListener { activity?.onBackPressed() }
        checkIsSeller()
    }

    private fun search() {
        binding.search.setOnClickListener {
            //api call
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
                    OrderViewModel.SellerStock.Seller -> {
                        binding.stock.visibility = View.GONE
                        newBook()
                        getBardCodeData()
                        cancel()
                        submit()
                        initAdapter()
                    }
                    OrderViewModel.SellerStock.Stock -> {
                        binding.stock.visibility = View.VISIBLE
                        isSeller = false
                        search()
                    }
                }
            }
        })
    }

    private fun initAdapter() {
        adapter = OrderAdapter(bookData)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
    }

    private fun submit() {
        binding.submit.setOnClickListener {
            //TODO api call
        }
    }

    private fun newBook() {
        binding.newBook.setOnClickListener { findNavController().navigate(R.id.action_orderFragment_to_orderScannerFragment) }
    }

    private fun cancel() {
        binding.cancel.setOnClickListener { activity?.onBackPressed() }
    }

    private fun getBardCodeData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("scanner_order")?.observe(viewLifecycleOwner, Observer {
            //scanner data
        })
    }

}