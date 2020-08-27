package com.book.store.stock.bookstore.pages.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class OrderViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences): ViewModel() {
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