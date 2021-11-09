package com.example.headsup2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val activity: UpdateDelete, var arr : ArrayList<CelebrityModel> ): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

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
            tv_Name.text = data.Name
            tv_T1.text = data.Taboo1
            tv_T2.text = data.Taboo2
            tv_T3.text = data.Taboo3

            bUpdate.setOnClickListener {
                activity.ShowAlert(data)
            }

            bDelete.setOnClickListener {
                activity.delete(data)
            }
        }
    }

    override fun getItemCount() = arr.size

    fun Update(CelebrityArray: ArrayList<CelebrityModel>) {
        this.arr = CelebrityArray
        notifyDataSetChanged()
    }

}