package com.book.store.stock.bookstore.data.net.response.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class User(
    @PrimaryKey
    @Expose
    val email: String? = null,
    val role: String? = null,
    val lastName: String? = null,
    val firstName: String? = null

)