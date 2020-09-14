package com.book.store.stock.bookstore.data.net

import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.data.net.response.seller.book_list.order.RequestOrder
import com.book.store.stock.bookstore.data.net.response.user.ResponseToken
import com.book.store.stock.bookstore.data.net.response.user.User
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    companion object {
        const val BASE_URL = "https://192.168.1.111:8000"
    }

    //user
    @POST("/api/token/")
    @FormUrlEncoded
    fun getToken(@Field("username") userName: String, @Field("password") password: String): Call<ResponseToken>

    @GET("/profile/")
    fun getUserInfo(): Call<User>


    //seller
    @GET("/books/")
    fun getBookListSeller(): Call<ResponseBookListSeller>

    @GET("/books/{filter}")
    fun getBookListSellerFilter(@Path("filter") filter: String): Call<ResponseBookListSeller>

    @GET("/orders/create/")
    fun orderSeller(orderRequest: RequestOrder): Call<String>

    /*@GET("/orders/")
    fun getOrderStock():Call*/

    @GET("books/?author=&name=%DA%A9%D8%AA%D8%A7%D8%A8+%D8%AE%D9%88%D8%A8%DB%8C+%D8%A8%D9%88%D8%AF&ordering=name&published_date=")
    fun search(
        @Query("author") author: String? = null,
        @Query("name") name: String? = null,
        @Query("ordering") ordering: String? = null,
        @Query("published_date") published_date: String? = null
    ): Call<ResponseSearch>

    //stockClerk

}