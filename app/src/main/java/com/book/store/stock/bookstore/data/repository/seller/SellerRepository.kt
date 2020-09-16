package com.book.store.stock.bookstore.data.repository.seller

import androidx.lifecycle.LiveData
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.PublisherList
import com.book.store.stock.bookstore.data.net.response.ResponseOrderDetail
import com.book.store.stock.bookstore.data.net.response.ResponseRequest
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
import retrofit2.Call
import retrofit2.http.Path

interface SellerRepository {

    fun getAllBooks(): LiveData<BaseResponse<ResponseBookListSeller>>
    fun getAllBooksFilter(filter: String): LiveData<BaseResponse<ResponseBookListSeller>>
    fun order(requestOrder: RequestOrder): LiveData<BaseResponse<String>>
    fun search(
        author: String? = null,
        name: String? = null,
        ordering: String? = null,
        published_date: String? = null
    ): LiveData<BaseResponse<ResponseSearch>>

    fun request(): LiveData<BaseResponse<ResponseRequest>>
    fun requestDetail(id:String):LiveData<BaseResponse<ResponseOrderDetail>>

    fun getPublisher(name:String):LiveData<BaseResponse<PublisherList>>
}