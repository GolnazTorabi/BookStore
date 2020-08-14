package com.book.store.stock.bookstore.pages.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.OrderFragmentBinding
import com.book.store.stock.bookstore.pages.dash_board.DashBoardViewModel
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

                    }
                    OrderViewModel.SellerStock.Stock -> {
                        isSeller = false
                        search()
                    }
                }
            }
        })
    }

}