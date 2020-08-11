package com.book.store.stock.bookstore.pages.dash_board.filter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class DashBoardFilterFragment : Fragment() {

    companion object {
        fun newInstance() = DashBoardFilterFragment()
    }

    private lateinit var viewModel: DashBoardFilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_filter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DashBoardFilterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}