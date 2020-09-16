package com.book.store.stock.bookstore.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.book.store.stock.bookstore.data.net.response.user.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user limit 1")
    fun get(): User

    @Insert
    fun insert(vararg user: User?)

    @Delete
    fun delete(user: User?)
}
