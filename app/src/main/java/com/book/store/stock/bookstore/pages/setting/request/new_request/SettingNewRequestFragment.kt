package com.book.store.stock.bookstore.pages.setting.request.new_request

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingNewRequestFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingNewRequestFragment()
    }

    private lateinit var viewModel: SettingNewRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_new_request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingNewRequestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}