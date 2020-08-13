package com.book.store.stock.bookstore.pages.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingFragmentBinding
import com.book.store.stock.bookstore.pages.StartActivity
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel
    private lateinit var binding: SettingFragmentBinding
    private var isSeller: Boolean? = false

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(SettingViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.back.setOnClickListener { activity?.onBackPressed() }
        goToSearch()
        goToReports()
        goToRequest()
        goToNewBook()
        exitFromApp()
        checkIsSeller()
    }

    private fun checkIsSeller() {
        viewModel.isSeller()
        viewModel.isSeller.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    SettingViewModel.IsSeller.Seller -> {
                        isSeller = true
                        binding.newBooks.visibility = View.GONE
                    }
                    SettingViewModel.IsSeller.Stock -> {
                        isSeller = false
                        binding.requests.visibility = View.GONE
                    }
                }

            }
        })
    }

    private fun exitFromApp() {
        binding.exitLayout.setOnClickListener {
            appSharedPreferences.removeAuthToken()
            activity?.finish()
            startActivity(Intent(context, StartActivity::class.java))
        }
    }

    private fun goToNewBook() {
        binding.newBooks.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_settingNewBookFragment) }
    }

    private fun goToRequest() {
        binding.requests.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_settingRequestSellerFragment) }
    }

    private fun goToReports() {
        when (isSeller) {
            true -> binding.reports.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_settingReportSellerFragment) }
            else -> binding.reports.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_settingReportStockClerkFragment) }
        }
    }

    private fun goToSearch() {
        binding.searchLayout.setOnClickListener { findNavController().navigate(R.id.action_settingFragment_to_settingSearchFragment) }
    }

}