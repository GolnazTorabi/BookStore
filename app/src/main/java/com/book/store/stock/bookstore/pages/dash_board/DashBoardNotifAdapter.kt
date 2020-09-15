package com.book.store.stock.bookstore.pages.dash_board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.data.net.response.ResponseRequestItem
import com.book.store.stock.bookstore.databinding.BookListItemsBinding
import com.book.store.stock.bookstore.databinding.NotificationListBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener

class DashBoardNotifAdapter (var list: ArrayList<ResponseRequestItem>, var onLoadMoreListener: LoadMoreListener,var onItemSelect: onItemSelect): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: NotificationListBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.notification_list, parent, false)
        return NotificationListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotificationListViewHolder){
            holder.binding.layout.setOnClickListener {
                onItemSelect.select((position+1).toString())
            }
        }

        if (position==list.size-1)
            onLoadMoreListener.onLoadMore()
    }
    class NotificationListViewHolder(var binding: NotificationListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}