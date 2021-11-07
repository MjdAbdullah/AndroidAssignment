package com.example.encoder_decoder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val context: Context, var arr : ArrayList<Phrase> ): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
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
            tvTextOld.text = data.originalPhrase
            tvTextNew.text = data.newPhrase
        }
    }

    override fun getItemCount() = arr.count()
    fun Update(arr : ArrayList<Phrase>){
        this.arr = arr
        notifyDataSetChanged()
    }
}