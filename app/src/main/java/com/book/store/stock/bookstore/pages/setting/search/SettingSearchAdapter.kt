package com.book.store.stock.bookstore.pages.setting.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.data.net.response.search.ResponseSearch
import com.book.store.stock.bookstore.databinding.BookListItemsBinding
import com.book.store.stock.bookstore.databinding.SettingSearchHeaderBinding
import com.book.store.stock.bookstore.utility.LoadMoreListener

private var layoutInflater: LayoutInflater? = null

class SettingSearchAdapter(var list: ResponseSearch, var onSearchClicked: OnSearchClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val HeaderType = 0
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
                    holder.binding.topicCheckBox.isChecked
                    /*holder.binding.mostSaleCheckBox.isChecked,
                    holder.binding.publisherOrderCheckBox.isChecked,
                    holder.binding.writerCheckBox.isChecked*/
                )
            }
        } else if (holder is BookListViewHolder) {
            holder.binding.name.text = list[position].name
            holder.binding.translatorName.text = list[position].author
            holder.binding.price.text = list[position].price.toString()
            holder.binding.stock.text = list[position].stock.toString()
            holder.binding.publishYear.text = list[position].published_date
            holder.binding.publishedTime.text = list[position].edition.toString()
            //holder.binding.publisher.text = list[position]
        }
    }

    class BookListViewHolder(var binding: BookListItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    class SearchHeaderViewHolder(var binding: SettingSearchHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}