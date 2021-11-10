package com.example.headsup_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headsup_room.Data.Celebrity
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val activity: ViewActivity): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    private var celebrityList = emptyList<Celebrity>()

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
        val Details = celebrityList[position]
        holder.itemView.apply {
            tv_Name.text = Details.Name
            tv_T1.text = Details.Taboo1
            tv_T2.text = Details.Taboo2
            tv_T3.text = Details.Taboo3
            ibUpdate.setOnClickListener {
                activity.UpdateAlert(Details)
            }
            ibDelete.setOnClickListener {
                activity.DeleteAlert(Details)
            }
        }
    }

    override fun getItemCount() = celebrityList.count()

    fun Update(celebrity: List<Celebrity>) {
        this.celebrityList = celebrity
        notifyDataSetChanged()
    }
}