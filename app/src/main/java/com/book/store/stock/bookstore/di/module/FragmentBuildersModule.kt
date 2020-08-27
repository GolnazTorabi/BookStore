package com.book.store.stock.bookstore.di.module

import com.book.store.stock.bookstore.pages.authentication.login.LoginFragment
import com.book.store.stock.bookstore.pages.authentication.register.RegisterFragment
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenFragment
import com.book.store.stock.bookstore.pages.dash_board.DashBoardFragment
import com.book.store.stock.bookstore.pages.dash_board.filter.DashBoardFilterFragment
import com.book.store.stock.bookstore.pages.order.OrderFragment
import com.book.store.stock.bookstore.pages.setting.SettingFragment
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookFragment
import com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book.SettingAddNewBookFragment
import com.book.store.stock.bookstore.pages.setting.report.seller.SettingReportSellerFragment
import com.book.store.stock.bookstore.pages.setting.report.stock_clerk.SettingReportStockClerkFragment
import com.book.store.stock.bookstore.pages.setting.request.seller.SettingRequestSellerFragment
import com.book.store.stock.bookstore.pages.setting.request.seller.new_request.SettingNewRequestFragment
import com.book.store.stock.bookstore.pages.setting.search.SettingSearchFragment
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

    @ContributesAndroidInjector
    abstract fun contributeSettingNewBookFragment(): SettingNewBookFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingAddNewBookFragment(): SettingAddNewBookFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingReportSellerFragment(): SettingReportSellerFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingReportStockFragment(): SettingReportStockClerkFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingRequestSellerFragment(): SettingRequestSellerFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingNewRequestFragment(): SettingNewRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingSearchFragment(): SettingSearchFragment

}