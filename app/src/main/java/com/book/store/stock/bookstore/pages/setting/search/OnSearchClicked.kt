package com.book.store.stock.bookstore.pages.setting.search

interface OnSearchClicked {
    fun onSearchClicked(text:String,book:Boolean,writer:Boolean,publisher:Boolean,topic:Boolean/*,mostSale:Boolean,publisherOrder:Boolean,writerName:Boolean*/)
}