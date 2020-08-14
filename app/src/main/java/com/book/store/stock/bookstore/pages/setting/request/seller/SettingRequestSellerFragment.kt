package com.book.store.stock.bookstore.pages.setting.request.seller

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
import com.book.store.stock.bookstore.databinding.SettingRequestSellerFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingRequestSellerFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SettingRequestSellerFragment()
    }

    private lateinit var viewModel: SettingRequestSellerViewModel
    private lateinit var binding: SettingRequestSellerFragmentBinding
    private lateinit var adapter: SettingRequestSellerAdapter

    private var bookList = ArrayList<String>()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_request_seller_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingRequestSellerViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        getNewBookData()
        goToAddNewBook()
        submitData()

    }

    private fun submitData() {
        binding.submit.setOnClickListener {
            //TODO api call
        }
    }

    private fun goToAddNewBook() {
        binding.newBook.setOnClickListener { findNavController().navigate(R.id.action_settingRequestSellerFragment_to_settingNewRequestFragment) }
    }

    private fun initAdapter() {
        adapter = SettingRequestSellerAdapter(bookList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
    }

    private fun getNewBookData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("book_data")?.observe(viewLifecycleOwner, Observer {
            bookList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }


}