package com.book.store.stock.bookstore.pages.authentication.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private var appSharedPreferences: AppSharedPreferences) :
    ViewModel() {
    var isLogIn = MutableLiveData<LoginOrNot>()

    fun checkLoginUser() {
        when (appSharedPreferences.getLogin()) {
            true -> isLogIn.value = LoginOrNot.LogIn
            else -> isLogIn.value = LoginOrNot.NotLogin
        }
    }


    enum class LoginOrNot {
        LogIn,
        NotLogin
    }
}