package com.book.store.stock.bookstore

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RequestShowDataFragment : Fragment() {

    companion object {
        fun newInstance() = RequestShowDataFragment()
    }

    private lateinit var viewModel: RequestShowDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_show_data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequestShowDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}