package com.book.store.stock.bookstore.data.repository.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.book.store.stock.bookstore.data.db.UserDao
import com.book.store.stock.bookstore.data.net.ApiInterface
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.RegisterRequest
import com.book.store.stock.bookstore.data.net.response.user.ResponseToken
import com.book.store.stock.bookstore.data.net.response.user.User
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import com.book.store.stock.bookstore.utility.ErrorUtils
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var appSharedPreferences: AppSharedPreferences,
    private var userDao: UserDao,
    private var apiInterface: ApiInterface
) : UserRepository {
    override fun getToken(userName: String, password: String): LiveData<BaseResponse<ResponseToken>> {
        val getToken = MutableLiveData<BaseResponse<ResponseToken>>()
        apiInterface.getToken(userName, password).enqueue(
            object : retrofit2.Callback<ResponseToken> {
                override fun onFailure(call: Call<ResponseToken>, t: Throwable) {
                    getToken.value = ErrorUtils.parseError()
                }

                override fun onResponse(call: Call<ResponseToken>, response: Response<ResponseToken>) {
                    if (response.isSuccessful) {
                        getToken.value = BaseResponse.success(response.body())
                        saveToken(response.body()?.refresh)
                    } else {
                        getToken.value = ErrorUtils.parseError(response)
                    }
                }
            }
        )
        return getToken
    }

    override fun userInfo(): LiveData<BaseResponse<User>> {
        val user = MutableLiveData<BaseResponse<User>>()
        apiInterface.getUserInfo().enqueue(
            object : retrofit2.Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    user.value = ErrorUtils.parseError()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        user.value = BaseResponse.success(response.body())
                        saveUser(response.body())
                    } else {
                        user.value = ErrorUtils.parseError(response)
                    }
                }
            }
        )
        return user
    }

    override fun register(registerRequest: RegisterRequest): LiveData<BaseResponse<RegisterRequest>> {
        val user = MutableLiveData<BaseResponse<RegisterRequest>>()
        apiInterface.register(registerRequest).enqueue(
            object : retrofit2.Callback<RegisterRequest> {
                override fun onFailure(call: Call<RegisterRequest>, t: Throwable) {
                    user.value = ErrorUtils.parseError()
                }

                override fun onResponse(call: Call<RegisterRequest>, response: Response<RegisterRequest>) {
                    if (response.isSuccessful) {
                        user.value = BaseResponse.success(response.body())
                    } else {
                        user.value = ErrorUtils.parseError(response)
                    }
                }
            }
        )
        return user
    }

    private fun saveUser(user: User?) {
        userDao.delete(user)
        userDao.insert(user)
        when (user?.role) {
            "se" -> appSharedPreferences.isSeller(true)
            else -> appSharedPreferences.isSeller(false)
        }
    }

    private fun saveToken(token: String?) {
        appSharedPreferences.setAuthToken(token)
    }

}