package com.book.store.stock.bookstore.di.module

import com.book.store.stock.bookstore.pages.authentication.login.LoginFragment
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashScreenFragment

}