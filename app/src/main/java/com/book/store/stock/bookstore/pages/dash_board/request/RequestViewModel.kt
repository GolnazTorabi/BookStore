package com.book.store.stock.bookstore.pages.dash_board.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.ResponseOrderDetail
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import javax.inject.Inject

class RequestViewModel@Inject constructor(var sellerRepository: SellerRepository) : ViewModel() {
    val order = MutableLiveData<ResponseOrderDetail>()



    fun orderSeller(id: String) {
        sellerRepository.requestDetail(id).observeForever {
            when (it.status) {
                BaseResponse.Status.Success -> order.value = it.data
            }
        }
    }


}