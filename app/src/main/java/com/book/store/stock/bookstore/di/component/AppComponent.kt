package com.book.store.stock.bookstore.di.component

import android.app.Application
import com.book.store.stock.bookstore.di.module.ActivityModule
import com.book.store.stock.bookstore.di.module.AppModule
import com.book.store.stock.bookstore.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
