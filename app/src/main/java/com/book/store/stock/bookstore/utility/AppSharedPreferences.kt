package com.book.store.stock.bookstore.utility

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shared preferences", 0)

    fun setAuthToken(authToken: String?) {
        sharedPreferences.edit()
            .putString("token", "Bearer $authToken")
            .apply()


    }

    fun getAuthToken(): String {
        return sharedPreferences.getString("token", "")!!
    }

    fun setLogIn(authToken: Boolean?) {
        sharedPreferences.edit()
            .putBoolean("login", authToken ?: false)
            .apply()


    }

    fun getLogin(): Boolean {
        return sharedPreferences.getBoolean("login", false)
    }

}