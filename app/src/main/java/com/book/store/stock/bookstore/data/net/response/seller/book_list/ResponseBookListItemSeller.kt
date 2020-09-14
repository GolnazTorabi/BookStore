package com.book.store.stock.bookstore.data.net.response.seller.book_list

data class ResponseBookListItemSeller(
    val author: String,
    val barcode: String,
    val edition: Int,
    val name: String,
    val price: Int,
    val published_date: String,
    val stock: Int,
    val topic: String
)