package com.book.store.stock.bookstore.data.net.response.user

import com.google.gson.annotations.SerializedName

data class ResponseToken(

    @field:SerializedName("access")
    val access: String? = null,

    @field:SerializedName("refresh")
    val refresh: String? = null
)