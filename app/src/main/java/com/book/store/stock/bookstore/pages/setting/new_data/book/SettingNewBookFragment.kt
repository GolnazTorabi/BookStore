package com.book.store.stock.bookstore.pages.setting.new_data.book

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingNewBookFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingNewBookFragment()
    }

    private lateinit var viewModel: SettingNewBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_new_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingNewBookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}