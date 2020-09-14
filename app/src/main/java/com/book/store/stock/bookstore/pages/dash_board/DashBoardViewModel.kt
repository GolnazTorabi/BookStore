package com.book.store.stock.bookstore.pages.dash_board

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import com.book.store.stock.bookstore.data.repository.stock_clerk.StockClerkRepository
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences, var sellerRepository: SellerRepository, var stockClerkRepository: StockClerkRepository) : ViewModel() {
    val isSeller = MutableLiveData<SellerStock>()
    val bookStatus = MutableLiveData<DashboardStatus>()
    val books = MutableLiveData<ResponseBookListSeller>()

    fun isSeller() {
        if (appSharedPreferences.getSeller()) {
            //Seller
            isSeller.value = SellerStock.Seller
        } else {
            //Stock
            isSeller.value = SellerStock.Seller
        }
    }

    fun requestGetAllBook(filter: String = "") {
        if (filter.isBlank()) {
            bookStatus.value = DashboardStatus.ShowLoading
            sellerRepository.getAllBooks().observeForever {
                bookStatus.value = DashboardStatus.HideLoading
                when (it.status) {
                    BaseResponse.Status.Success -> books.value = it.data
                    BaseResponse.Status.BadRequest -> bookStatus.value = DashboardStatus.Fail
                }
            }
        }else {
            bookStatus.value = DashboardStatus.ShowLoading
            sellerRepository.getAllBooksFilter(filter).observeForever {
                bookStatus.value = DashboardStatus.HideLoading
                when (it.status) {
                    BaseResponse.Status.Success -> books.value = it.data
                    BaseResponse.Status.BadRequest -> bookStatus.value = DashboardStatus.Fail
                }
            }
        }

    }


    enum class SellerStock {
        Seller,
        Stock
    }

    enum class DashboardStatus {
        Success,
        Fail,
        ShowLoading,
        HideLoading
    }
}