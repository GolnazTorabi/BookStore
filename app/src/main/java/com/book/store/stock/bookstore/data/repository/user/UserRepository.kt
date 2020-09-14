package com.book.store.stock.bookstore.data.repository.user

import androidx.lifecycle.LiveData
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.user.ResponseToken
import com.book.store.stock.bookstore.data.net.response.user.User
import retrofit2.Call

interface UserRepository {

    fun getToken(userName: String, password: String): LiveData<BaseResponse<ResponseToken>>

    fun userInfo():LiveData<BaseResponse<User>>
}