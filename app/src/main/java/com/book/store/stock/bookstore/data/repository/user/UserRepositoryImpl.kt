package com.book.store.stock.bookstore.data.repository.user

import com.book.store.stock.bookstore.data.db.UserDao
import com.book.store.stock.bookstore.data.net.ApiInterface
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var appSharedPreferences: AppSharedPreferences,
    private var userDao: UserDao,
    private var apiInterface: ApiInterface
) : UserRepository {

}