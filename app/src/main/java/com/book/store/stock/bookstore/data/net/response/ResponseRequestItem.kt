package com.book.store.stock.bookstore.data.net.response

data class ResponseRequestItem(
    val book: BookX,
    val id: Int,
    val quantity: Int
)