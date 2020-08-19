package com.book.store.stock.bookstore.pages

import android.os.Bundle
import com.book.store.stock.bookstore.R
import dagger.android.support.DaggerAppCompatActivity


class StartActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


    }

}