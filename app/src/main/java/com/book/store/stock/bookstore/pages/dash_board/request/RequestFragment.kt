package com.book.store.stock.bookstore.pages.dash_board.request

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class RequestFragment : Fragment() {

    companion object {
        fun newInstance() =
            RequestFragment()
    }

    private lateinit var viewModel: RequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}