package com.book.store.stock.bookstore.pages.dash_board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.data.net.response.seller.book_list.ResponseBookListSeller
import com.book.store.stock.bookstore.databinding.BookListItemsBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener

class DashBoardAdapter(var list: ResponseBookListSeller,var onLoadMoreListener: LoadMoreListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: BookListItemsBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.book_list_items, parent, false)
        return BookListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookListViewHolder){
            holder.binding.name.text = list[position].name
            //holder.binding.translatorName.text =
            holder.binding.price.text = list[position].price.toString()
            holder.binding.stock.text = list[position].stock.toString()
            holder.binding.publishYear.text = list[position].edition.toString()
            holder.binding.publishedTime.text = list[position].published_date.toString()
            holder.binding.publisher.text = list[position].author
        }

        if (position==list.size-1)
            onLoadMoreListener.onLoadMore()
    }
    class BookListViewHolder(var binding: BookListItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}