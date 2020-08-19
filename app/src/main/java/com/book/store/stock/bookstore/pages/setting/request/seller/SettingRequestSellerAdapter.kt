package com.book.store.stock.bookstore.pages.setting.request.seller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SettingRequestNewBookItemsBinding

class SettingRequestSellerAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: SettingRequestNewBookItemsBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.setting_request_new_book_items, parent, false)
        return SettingRequestNewBookItemsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SettingRequestNewBookItemsViewHolder) {
            holder.binding.bookName.text = list[position]
            holder.binding.writer.text = list[position]
            holder.binding.publisher.text = list[position]
            holder.binding.count.text = list[position]
        }
    }

    class SettingRequestNewBookItemsViewHolder(var binding: SettingRequestNewBookItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}