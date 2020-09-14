package com.book.store.stock.bookstore.data.repository.seller

import androidx.lifecycle.LiveData
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder

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
}