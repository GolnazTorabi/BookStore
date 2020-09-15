package com.book.store.stock.bookstore.data.repository.seller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.book.store.stock.bookstore.data.db.UserDao
import com.book.store.stock.bookstore.data.net.ApiInterface
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.ResponseOrderDetail
import com.book.store.stock.bookstore.data.net.response.ResponseRequest
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
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

    override fun order(requestOrder: RequestOrder): LiveData<BaseResponse<String>> {
        val books = MutableLiveData<BaseResponse<String>>()
        apiInterface.orderSeller(requestOrder).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    books.value = BaseResponse.success(response.body())
                } else {
                    books.value = ErrorUtils.parseError(response)
                }
            }

        })
        return books
    }

    override fun search(author: String?, name: String?, ordering: String?, published_date: String?): LiveData<BaseResponse<ResponseSearch>> {
        val books = MutableLiveData<BaseResponse<ResponseSearch>>()
        apiInterface.search(author, name, ordering, published_date).enqueue(object : Callback<ResponseSearch> {
            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseSearch>, response: Response<ResponseSearch>) {
                if (response.isSuccessful) {
                    books.value = BaseResponse.success(response.body())
                } else {
                    books.value = ErrorUtils.parseError(response)
                }
            }

        })
        return books
    }

    override fun request(): LiveData<BaseResponse<ResponseRequest>> {
        val books = MutableLiveData<BaseResponse<ResponseRequest>>()
        apiInterface.request().enqueue(object : Callback<ResponseRequest> {
            override fun onFailure(call: Call<ResponseRequest>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseRequest>, response: Response<ResponseRequest>) {
                if (response.isSuccessful) {
                    books.value = BaseResponse.success(response.body())
                } else {
                    books.value = ErrorUtils.parseError(response)
                }
            }

        })
        return books
    }

    override fun requestDetail(id: String): LiveData<BaseResponse<ResponseOrderDetail>> {
        val books = MutableLiveData<BaseResponse<ResponseOrderDetail>>()
        apiInterface.requestDetail(id).enqueue(object : Callback<ResponseOrderDetail> {
            override fun onFailure(call: Call<ResponseOrderDetail>, t: Throwable) {
                books.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseOrderDetail>, response: Response<ResponseOrderDetail>) {
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