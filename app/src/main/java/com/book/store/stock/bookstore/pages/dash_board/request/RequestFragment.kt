package com.book.store.stock.bookstore.pages.dash_board.request

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.book.store.stock.bookstore.databinding.RequestFragmentBinding
import com.book.store.stock.bookstore.pages.order.OrderAdapter

class RequestFragment : Fragment() {

    companion object {
        fun newInstance() =
            RequestFragment()
    }

    private lateinit var viewModel: RequestViewModel
    private lateinit var binding: RequestFragmentBinding
    private lateinit var id: String
    private lateinit var adapter: OrderAdapter
    private lateinit var bookData: ArrayList<Item>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.request_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = OrderAdapter(bookData)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.requestList.layoutManager = layoutManager
        binding.requestList.adapter = adapter
        id = arguments?.getString("id", "")!!
        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)
        viewModel.orderSeller(id)
        viewModel.order.observe(viewLifecycleOwner, Observer {
            bookData.clear()
            for(i in 0..it.items.size){
                bookData.add(Item(it.items[i].book.name,it.items[i].quantity))
            }
            adapter.notifyDataSetChanged()

        })
        binding.seach.setOnClickListener { findNavController().navigate(R.id.action_requestFragment_to_setting_graph) }
        binding.answer.setOnClickListener { activity?.onBackPressed() }

    }

}