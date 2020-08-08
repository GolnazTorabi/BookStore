package com.book.store.stock.bookstore.di.component

import com.book.store.stock.bookstore.pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenViewModel
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
}