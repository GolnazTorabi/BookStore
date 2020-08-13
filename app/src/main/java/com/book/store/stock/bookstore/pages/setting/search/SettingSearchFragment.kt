package com.book.store.stock.bookstore.pages.setting.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingSearchFragmentBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener
import javax.inject.Inject

class SettingSearchFragment : DialogFragment(), OnSearchClicked, LoadMoreListener {

    companion object {
        fun newInstance() =
            SettingSearchFragment()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: SettingSearchViewModel
    private lateinit var binding: SettingSearchFragmentBinding
    private lateinit var bookAdapter: SettingSearchAdapter

    private var bookList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_search_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingSearchViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        bookAdapter = SettingSearchAdapter(listOf<String>("", "") as ArrayList<String>, this, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchList.layoutManager = layoutManager
        binding.searchList.adapter = bookAdapter
    }

    override fun onSearchClicked(text: String, book: Boolean, writer: Boolean, publisher: Boolean, topic: Boolean, mostSale: Boolean, publisherOrder: Boolean, writerName: Boolean) {
        //todo api call
    }

    override fun onLoadMore() {
        //todo api call
    }

}