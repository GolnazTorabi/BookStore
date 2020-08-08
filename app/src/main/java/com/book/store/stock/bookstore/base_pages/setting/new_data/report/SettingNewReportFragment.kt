package com.book.store.stock.bookstore.base_pages.setting.new_data.report

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.book.store.stock.bookstore.R

class SettingNewReportFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingNewReportFragment()
    }

    private lateinit var viewModel: SettingNewReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_new_report_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingNewReportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}