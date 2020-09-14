package com.book.store.stock.bookstore.pages.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.repository.user.UserRepository
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class LoginViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences, private var userRepository: UserRepository) : ViewModel() {
    val getTokenStatus = MutableLiveData<LoginStatus>()
    val errorMessage = MutableLiveData<String>()

    val seller = MutableLiveData<IsSeller>()

    fun requestForToken(userName: String, password: String) {
        getTokenStatus.value = LoginStatus.ShowLoading
        userRepository.getToken(userName, password).observeForever {
            getTokenStatus.value = LoginStatus.HideLoading
            when (it.status) {
                BaseResponse.Status.Success -> getTokenStatus.value = LoginStatus.Success
                BaseResponse.Status.BadRequest -> errorMessage.value = it.message
                BaseResponse.Status.NoInternet -> getTokenStatus.value = LoginStatus.NoInternet
            }
        }
    }

    fun requestUserRole() {
        when (appSharedPreferences.getSeller()) {
            true -> seller.value = IsSeller.Seller
            false -> seller.value = IsSeller.StockClerk
        }
    }

    enum class IsSeller {
        Seller,
        StockClerk
    }

    enum class LoginStatus {
        Success,
        Fail,
        ShowLoading,
        HideLoading,
        NoInternet
    }
}