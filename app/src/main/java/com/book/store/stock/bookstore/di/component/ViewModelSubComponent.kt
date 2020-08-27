package com.book.store.stock.bookstore.di.component

import com.book.store.stock.bookstore.pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.pages.authentication.register.RegisterViewModel
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenViewModel
import com.book.store.stock.bookstore.pages.dash_board.DashBoardViewModel
import com.book.store.stock.bookstore.pages.dash_board.filter.DashBoardFilterViewModel
import com.book.store.stock.bookstore.pages.order.OrderViewModel
import com.book.store.stock.bookstore.pages.setting.SettingViewModel
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookViewModel
import com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book.SettingAddNewBookViewModel
import com.book.store.stock.bookstore.pages.setting.report.seller.SettingReportSellerViewModel
import com.book.store.stock.bookstore.pages.setting.report.stock_clerk.SettingReportStockClerkViewModel
import com.book.store.stock.bookstore.pages.setting.request.seller.SettingRequestSellerViewModel
import com.book.store.stock.bookstore.pages.setting.request.seller.new_request.SettingNewRequestViewModel
import com.book.store.stock.bookstore.pages.setting.search.SettingSearchViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
//view model with function name
    fun loginViewModel():LoginViewModel
    fun splashViewModel():SplashScreenViewModel
    fun registerViewModel():RegisterViewModel
    fun dashboardViewModel():DashBoardViewModel
    fun dashboardFilterViewModel():DashBoardFilterViewModel
    fun orderViewModel():OrderViewModel
    fun settingViewModel():SettingViewModel
    fun newBookViewModel() : SettingNewBookViewModel
    fun addNewBookViewModel() :SettingAddNewBookViewModel
    fun reportSellerViewModel() : SettingReportSellerViewModel
    fun reportStockClerkViewModel() : SettingReportStockClerkViewModel
    fun requestSellerViewModel() : SettingRequestSellerViewModel
    fun newRequestViewModel() : SettingNewRequestViewModel
    fun searchViewModel() : SettingSearchViewModel
}