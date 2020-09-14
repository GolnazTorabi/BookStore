package com.book.store.stock.bookstore.pages.setting.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.databinding.SettingSearchFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingSearchFragment : DaggerFragment(), OnSearchClicked {

    companion object {
        fun newInstance() =
            SettingSearchFragment()
    }


    private lateinit var viewModel: SettingSearchViewModel
    private lateinit var binding: SettingSearchFragmentBinding
    private lateinit var bookAdapter: SettingSearchAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var bookList = ResponseSearch()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_search_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingSearchViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        bookAdapter = SettingSearchAdapter(bookList, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchList.layoutManager = layoutManager
        binding.searchList.adapter = bookAdapter
    }

    override fun onSearchClicked(text: String, book: Boolean, writer: Boolean, publisher: Boolean, topic: Boolean/*, mostSale: Boolean, publisherOrder: Boolean, writerName: Boolean*/) {
        viewModel.search(text, book, writer, publisher, topic)
        viewModel.bookData.observe(viewLifecycleOwner, Observer {
            it.let {
                bookList.clear()
                bookList.addAll(it)
                bookAdapter.notifyDataSetChanged()
            }
        })
    }


}