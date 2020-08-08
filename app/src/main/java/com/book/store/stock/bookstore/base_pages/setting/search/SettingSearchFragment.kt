package com.book.store.stock.bookstore.base_pages.setting.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingSearchFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingSearchFragment()
    }

    private lateinit var viewModel: SettingSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}