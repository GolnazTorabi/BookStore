package com.book.store.stock.bookstore.pages.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import javax.inject.Inject

class SettingViewModel @Inject constructor(var appSharedPreferences: AppSharedPreferences) : ViewModel() {
    val isSeller = MutableLiveData<IsSeller>()


    fun isSeller(){
        if(appSharedPreferences.getSeller()){
            isSeller.value = IsSeller.Seller
        } else {
            isSeller.value = IsSeller.Stock
        }
    }

    enum class IsSeller{
        Seller,
        Stock
    }

}