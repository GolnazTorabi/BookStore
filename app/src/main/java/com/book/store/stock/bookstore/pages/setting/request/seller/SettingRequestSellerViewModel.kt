package com.book.store.stock.bookstore.pages.setting.request.seller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class SettingRequestSellerViewModel @Inject constructor( var sellerRepository: SellerRepository): ViewModel() {
    val order = MutableLiveData<SellerStatus>()

    fun orderSeller(bookList: RequestOrder) {
        sellerRepository.order(bookList).observeForever {
            when (it.status) {
                BaseResponse.Status.Success -> order.value = SellerStatus.Success
                BaseResponse.Status.BadRequest -> order.value = SellerStatus.Fail
            }
        }
    }

    enum class SellerStatus {
        Success,
        Fail
    }
}