package com.book.store.stock.bookstore.pages.dash_board.request.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class RequestSearchFragment : Fragment() {

    companion object {
        fun newInstance() =
            RequestSearchFragment()
    }

    private lateinit var viewModel: RequestSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequestSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}