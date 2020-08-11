package com.book.store.stock.bookstore.pages.dash_board.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.DashBoardFilterFragmentBinding
import javax.inject.Inject

class DashBoardFilterFragment : Fragment() {

    companion object {
        fun newInstance() = DashBoardFilterFragment()
    }

    private lateinit var viewModel: DashBoardFilterViewModel
    private lateinit var binding: DashBoardFilterFragmentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dash_board_filter_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(DashBoardFilterViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.back.setOnClickListener { activity?.onBackPressed() }
        onFilterClicked()
    }

    private fun onFilterClicked() {
        binding.mostSale.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("mostSale", "mostSale")
            activity?.onBackPressed()
        }
        binding.news.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("news", "news")
            activity?.onBackPressed()
        }
        binding.bookName.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("bookName", "bookName")
            activity?.onBackPressed()
        }
        binding.publisher.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("publisher", "publisher")
            activity?.onBackPressed()
        }
    }

}