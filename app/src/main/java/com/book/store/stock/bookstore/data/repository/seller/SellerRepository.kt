package com.book.store.stock.bookstore.data.repository.seller

import androidx.lifecycle.LiveData
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller

interface SellerRepository {

    fun getAllBooks(): LiveData<BaseResponse<ResponseBookListSeller>>
    fun getAllBooksFilter(filter:String): LiveData<BaseResponse<ResponseBookListSeller>>
}