package com.book.store.stock.bookstore.di.module

import ViewModelFactory
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.book.store.stock.bookstore.data.db.AppDatabase
import com.book.store.stock.bookstore.data.net.ApiInterface
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import com.book.store.stock.bookstore.data.repository.seller.SellerRepositoryImpl
import com.book.store.stock.bookstore.data.repository.stock_clerk.StockClerkRepository
import com.book.store.stock.bookstore.data.repository.stock_clerk.StockClerkRepositoryImpl
import com.book.store.stock.bookstore.data.repository.user.UserRepository
import com.book.store.stock.bookstore.data.repository.user.UserRepositoryImpl
import com.book.store.stock.bookstore.di.component.ViewModelSubComponent
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Module(subcomponents = [ViewModelSubComponent::class])
internal class AppModule {
    @Inject
    @Singleton
    @Provides
    fun provideApiClient(appSharedPreferences: AppSharedPreferences): ApiInterface {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(ApiInterface::class.java)
    }


    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelSubComponent.build())
    }

    @Singleton
    @Inject
    @Provides
    fun provideLoggingInterceptor(appSharedPreferences: AppSharedPreferences): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                //Timber.tag("OkHttp").d(message)
            }
        })
        return logging

    }


    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "VANDAR-DB"
        )
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserRepository(repo: UserRepositoryImpl): UserRepository = repo

    @Provides
    fun provideSellerRepository(repo: SellerRepositoryImpl): SellerRepository = repo

    @Provides
    fun providelStockClerkRepository(repo: StockClerkRepositoryImpl): StockClerkRepository = repo


}