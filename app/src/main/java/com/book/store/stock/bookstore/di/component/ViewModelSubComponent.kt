package com.book.store.stock.bookstore.di.component

import com.book.store.stock.bookstore.base_pages.authentication.login.LoginViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
//view model with function name
    fun loginViewModel():LoginViewModel
}