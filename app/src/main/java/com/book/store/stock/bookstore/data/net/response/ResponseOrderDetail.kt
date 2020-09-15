package com.book.store.stock.bookstore.data.net.response

data class ResponseOrderDetail(
    val id: Int,
    val items: List<Item>
)