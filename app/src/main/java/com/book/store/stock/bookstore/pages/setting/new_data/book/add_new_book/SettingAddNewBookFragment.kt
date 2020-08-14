package com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R

class SettingAddNewBookFragment : Fragment() {

    companion object {
        fun newInstance() = SettingAddNewBookFragment()
    }

    private lateinit var viewModel: SettingAddNewBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_add_new_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingAddNewBookViewModel::class.java)
        // TODO: Use the ViewModel
        getBardCodeData()
    }

    private fun getBardCodeData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ArrayList<String>>("scanner")?.observe(viewLifecycleOwner, Observer {
            //scanner data
        })
    }

}