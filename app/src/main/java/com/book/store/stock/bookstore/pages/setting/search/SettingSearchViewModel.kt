package com.book.store.stock.bookstore.pages.setting.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.bookstore.data.net.BaseResponse
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.data.repository.seller.SellerRepository
import javax.inject.Inject

class SettingSearchViewModel @Inject constructor(val sellerRepository: SellerRepository) : ViewModel() {

    val bookData = MutableLiveData<ResponseSearch>()

    fun search(text: String, book: Boolean? = null, writer: Boolean? = null, publisher: Boolean? = null, topic: Boolean? = null) {
        when {
            book == true -> {
                sellerRepository.search(null,text,null,null).observeForever {
                    setData(it)
                }
            }
            writer == true -> {
                sellerRepository.search(text,null,null,null).observeForever {
                    setData(it)
                }
            }
            publisher == true -> {
                sellerRepository.search(null,null,null,text).observeForever {
                    setData(it)
                }
            }
            topic == true -> {
                sellerRepository.search(null,null,text,null).observeForever {
                    setData(it)
                }
            }
        }

    }
    private fun setData(it:BaseResponse<ResponseSearch>){
        when(it.status){
            BaseResponse.Status.Success -> bookData.value = it.data
        }
    }


}