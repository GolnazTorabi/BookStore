package com.book.store.stock.bookstore.di.module

import com.book.store.stock.bookstore.base_pages.authentication.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

}