package com.book.store.stock.bookstore.pages.setting.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.BookListItemsBinding
import com.book.store.stock.bookstore.databinding.SettingSearchHeaderBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener

private var layoutInflater: LayoutInflater? = null

class SettingSearchAdapter(var list: ArrayList<String>, var onLoadMoreListener: LoadMoreListener, var onSearchClicked: OnSearchClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val HeaderType = 0
        const val DataType = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            HeaderType -> {
                val binding: SettingSearchHeaderBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.setting_search_header, parent, false)
                SearchHeaderViewHolder(binding)
            }
            else -> {
                val binding: BookListItemsBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.book_list_items, parent, false)
                BookListViewHolder(binding)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchHeaderViewHolder) {
            holder.binding.searchIcon.setOnClickListener {
                onSearchClicked.onSearchClicked(
                    holder.binding.seachEdit.text.toString(),
                    holder.binding.bookNameCheckBox.isChecked,
                    holder.binding.bookWriterCheckBox.isChecked,
                    holder.binding.publisherCheckBox.isChecked,
                    holder.binding.topicCheckBox.isChecked,
                    holder.binding.mostSaleCheckBox.isChecked,
                    holder.binding.publisherOrderCheckBox.isChecked,
                    holder.binding.writerCheckBox.isChecked
                )
            }
        } else if (holder is BookListViewHolder) {
            holder.binding.name.text = list[position]
            holder.binding.translatorName.text = list[position]
            holder.binding.price.text = list[position]
            holder.binding.stock.text = list[position]
            holder.binding.publishYear.text = list[position]
            holder.binding.publishedTime.text = list[position]
            holder.binding.publisher.text = list[position]
        }
        if (position + 1 == list.size)
            onLoadMoreListener.onLoadMore()
    }

    class BookListViewHolder(var binding: BookListItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    class SearchHeaderViewHolder(var binding: SettingSearchHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}