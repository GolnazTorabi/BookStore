package com.book.store.stock.bookstore.pages.dash_board

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import com.book.store.stock.bookstore.data.repository.stock_clerk.StockClerkRepository
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences, sellerRepository: SellerRepository, stockClerkRepository: StockClerkRepository) : ViewModel() {
    val isSeller = MutableLiveData<SellerStock>()

    fun isSeller(){
        if(appSharedPreferences.getSeller()){
            //Seller
            isSeller.value = SellerStock.Seller
        }else {
            //Stock
            isSeller.value = SellerStock.Seller
        }
    }

    enum class SellerStock{
        Seller,
        Stock
    }
}