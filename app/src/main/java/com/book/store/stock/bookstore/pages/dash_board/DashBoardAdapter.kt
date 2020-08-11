package com.book.store.stock.bookstore.pages.dash_board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.utility.LoadMoreListener

class DashBoardAdapter(var list: ArrayList<String>,var onLoadMoreListener: LoadMoreListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}