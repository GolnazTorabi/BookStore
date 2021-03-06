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

    fun removeAuthToken() {
        sharedPreferences.edit()
            .remove("token")
            .apply()
    }


    fun setLogIn(login: Boolean?) {
        sharedPreferences.edit()
            .putBoolean("login", login ?: false)
            .apply()


    }

    fun getLogin(): Boolean {
        return sharedPreferences.getBoolean("login", false)
    }

    fun isSeller(seller: Boolean?) {
        sharedPreferences.edit()
            .putBoolean("seller", seller ?: false)
            .apply()


    }

    fun getSeller(): Boolean {
        return sharedPreferences.getBoolean("seller", false)
    }

}