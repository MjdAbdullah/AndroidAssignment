package com.example.rssfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.data
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(val activity: MainActivity, val arr: ArrayList<data>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = arr[position]
        holder.itemView.apply {
            tv.text = data.titel
            tv.setOnClickListener{
                activity.tvDes.text = data.des
                activity.tvDes.isVisible = true
            }
            activity.tvDes.setOnClickListener {
                activity.tvDes.isVisible = false
            }
        }
    }

    override fun getItemCount() = arr.count()
}