package com.book.store.stock.bookstore.pages.setting.report.seller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.NotificationListBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener

class SettingReportSellerAdapter (var list: ArrayList<String>,var onLoadMoreListener: LoadMoreListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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
            holder.binding.notificationText.text = list[position]
            holder.binding.notificationTime.text = list[position]
        }

        if (position==list.size-1)
            onLoadMoreListener.onLoadMore()
    }
    class NotificationListViewHolder(var binding: NotificationListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}