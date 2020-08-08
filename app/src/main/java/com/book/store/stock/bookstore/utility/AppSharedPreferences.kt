package com.book.store.stock.bookstore.utility

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shared preferences", 0)

    open fun setAuthToken(authToken: String?) {
        sharedPreferences.edit()
            .putString("token", "Bearer $authToken")
            .apply()


    }

    fun getAuthToken(): String {
        return sharedPreferences.getString("token", "")!!
    }

}