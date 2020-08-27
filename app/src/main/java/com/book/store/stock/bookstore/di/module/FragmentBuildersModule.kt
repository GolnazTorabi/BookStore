package com.book.store.stock.bookstore.di.module

import com.book.store.stock.bookstore.pages.authentication.login.LoginFragment
import com.book.store.stock.bookstore.pages.authentication.register.RegisterFragment
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenFragment
import com.book.store.stock.bookstore.pages.dash_board.DashBoardFragment
import com.book.store.stock.bookstore.pages.dash_board.filter.DashBoardFilterFragment
import com.book.store.stock.bookstore.pages.order.OrderFragment
import com.book.store.stock.bookstore.pages.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    abstract fun contributeDashBoardFragment(): DashBoardFragment

    @ContributesAndroidInjector
    abstract fun contributeDashBoardFilterFragment(): DashBoardFilterFragment
    @ContributesAndroidInjector
    abstract fun contributeOrderFragment(): OrderFragment
    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment

}