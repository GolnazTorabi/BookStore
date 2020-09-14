package com.book.store.stock.bookstore.data.net.response

data class Book(
    val author: String? = null,
    val barcode: String?= null,
    val edition: Int? = null,
    val id: Int? = null,
    val name: String,
    val price: Int? = null,
    val published_date: String? = null,
    val publisher: String? = null,
    val stock: Int,
    val topic: String? = null
)