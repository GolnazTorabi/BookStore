package com.book.store.stock.bookstore.data.net.response

data class BookX(
    val author: String,
    val barcode: String,
    val edition: Int,
    val id: Int,
    val name: String,
    val price: Int,
    val published_date: String,
    val publisher: String,
    val stock: Int,
    val topic: String
)