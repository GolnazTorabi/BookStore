package com.book.store.stock.bookstore.data.net.response

data class RegisterRequest(
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val personal_code: String,
    val role: String,
    val username: String
)