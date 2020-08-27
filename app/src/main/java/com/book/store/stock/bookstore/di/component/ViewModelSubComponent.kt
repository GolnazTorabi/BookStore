package com.book.store.stock.bookstore.di.component

import com.book.store.stock.bookstore.pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.pages.authentication.register.RegisterViewModel
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenViewModel
import com.book.store.stock.bookstore.pages.dash_board.DashBoardViewModel
import com.book.store.stock.bookstore.pages.dash_board.filter.DashBoardFilterViewModel
import com.book.store.stock.bookstore.pages.order.OrderViewModel
import com.book.store.stock.bookstore.pages.setting.SettingViewModel
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
    //fun dashboardFilterViewModel():DashBoardFilterViewModel
    fun orderViewModel():OrderViewModel
    fun settingViewModel():SettingViewModel
}