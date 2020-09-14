package com.book.store.stock.bookstore.data.repository.seller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.book.store.stock.bookstore.data.db.UserDao
import com.book.store.stock.bookstore.data.net.ApiInterface
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import com.book.store.stock.bookstore.utility.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SellerRepositoryImpl @Inject constructor(
    var appSharedPreferences: AppSharedPreferences,
    private var userDao: UserDao,
    private var apiInterface: ApiInterface
) : SellerRepository {
    override fun getAllBooks(): LiveData<BaseResponse<ResponseBookListSeller>> {
        val books = MutableLiveData<BaseResponse<ResponseBookListSeller>>()
        apiInterface.getBookListSeller().enqueue(object : Callback<ResponseBookListSeller> {
            override fun onFailure(call: Call<ResponseBookListSeller>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseBookListSeller>, response: Response<ResponseBookListSeller>) {
                if (response.isSuccessful) {
                    books.value = BaseResponse.success(response.body())
                } else {
                    books.value = ErrorUtils.parseError(response)
                }
            }

        })
        return books
    }

    override fun getAllBooksFilter(filter: String): LiveData<BaseResponse<ResponseBookListSeller>> {
        val books = MutableLiveData<BaseResponse<ResponseBookListSeller>>()
        apiInterface.getBookListSellerFilter(filter).enqueue(object : Callback<ResponseBookListSeller> {
            override fun onFailure(call: Call<ResponseBookListSeller>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseBookListSeller>, response: Response<ResponseBookListSeller>) {
                if (response.isSuccessful) {
                    books.value = BaseResponse.success(response.body())
                } else {
                    books.value = ErrorUtils.parseError(response)
                }
            }

        })
        return books
    }
}