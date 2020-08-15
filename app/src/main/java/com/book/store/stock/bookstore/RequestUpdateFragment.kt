package com.book.store.stock.bookstore

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RequestUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = RequestUpdateFragment()
    }

    private lateinit var viewModel: RequestUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_update_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequestUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}