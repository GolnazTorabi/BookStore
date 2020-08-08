package com.book.store.stock.bookstore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.book.store.stock.bookstore.data.net.response.user.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao


}
