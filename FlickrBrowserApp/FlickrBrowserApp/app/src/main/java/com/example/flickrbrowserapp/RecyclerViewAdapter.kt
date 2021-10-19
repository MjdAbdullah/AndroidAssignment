package com.example.flickrbrowserapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(val activity: MainActivity, val arr: ArrayList<Image>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
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
            tvNameImage.text = data.Title
            Glide.with(activity).load(data.Link).into(ivImage)

            // if user click in the photo will Show it in big image view.
            ivImage.setOnClickListener {
                Glide.with(activity).load(data.Link).into(activity.iv)
                activity.iv.isVisible = true
            }
            // if user click in the big image view will close it.
            activity.iv.setOnClickListener {
                activity.iv.isVisible = false
            }
        }
    }

    override fun getItemCount() = arr.count()
}