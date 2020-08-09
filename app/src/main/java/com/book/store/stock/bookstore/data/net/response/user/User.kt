package com.book.store.stock.bookstore.data.net.response.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class User (
    @PrimaryKey
    @Expose
    var id:Int,
    var name:String
)