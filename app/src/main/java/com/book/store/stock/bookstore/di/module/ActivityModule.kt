package com.book.store.stock.bookstore.di.module

import com.book.store.stock.bookstore.pages.MainActivity
import com.book.store.stock.bookstore.pages.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeStartActivity(): StartActivity

}