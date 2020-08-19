package com.book.store.stock.bookstore.pages.dash_board.request.answer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class RequestAnswerFragment : Fragment() {

    companion object {
        fun newInstance() =
            RequestAnswerFragment()
    }

    private lateinit var viewModel: RequestAnswerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_answer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RequestAnswerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}