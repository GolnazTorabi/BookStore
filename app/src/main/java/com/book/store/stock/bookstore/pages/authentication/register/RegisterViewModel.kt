package com.book.store.stock.bookstore.pages.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.RegisterRequest
import com.book.store.stock.bookstore.data.repository.user.UserRepository
import com.book.store.stock.bookstore.pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class RegisterViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences,val  userRepository: UserRepository) : ViewModel() {
    val getTokenStatus = MutableLiveData<LoginStatus>()
    fun register(registerRequest: RegisterRequest){
        userRepository.register(registerRequest).observeForever {
            when(it.status){
                BaseResponse.Status.Success -> getTokenStatus.value = LoginStatus.Success
            }
        }
    }
    enum class LoginStatus {
        Success,
        Fail,
        ShowLoading,
        HideLoading,
        NoInternet
    }
}