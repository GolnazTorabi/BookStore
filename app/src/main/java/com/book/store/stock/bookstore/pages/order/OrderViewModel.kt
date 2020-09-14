package com.book.store.stock.bookstore.pages.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class OrderViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences, var sellerRepository: SellerRepository) : ViewModel() {
    val isSeller = MutableLiveData<SellerStock>()
    val order = MutableLiveData<SellerStatus>()

    fun isSeller() {
        if (appSharedPreferences.getSeller()) {
            //Seller
            isSeller.value = SellerStock.Seller
        } else {
            //Stock
            isSeller.value = SellerStock.Seller
        }
    }

    fun orderSeller(bookList: RequestOrder) {
        sellerRepository.order(bookList).observeForever {
            when (it.status) {
                BaseResponse.Status.Success -> order.value = SellerStatus.Success
                BaseResponse.Status.BadRequest -> order.value = SellerStatus.Fail
            }
        }
    }

    enum class SellerStock {
        Seller,
        Stock
    }

    enum class SellerStatus {
        Success,
        Fail
    }
}