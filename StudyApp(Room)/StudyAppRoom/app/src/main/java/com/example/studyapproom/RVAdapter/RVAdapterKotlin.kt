package com.example.studyapproom.RVAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapproom.Data.KotlinDetails
import com.example.studyapproom.Kotlin
import com.example.studyapproom.R
import com.example.studyapproom.ShowAlert
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapterKotlin (val activity: Kotlin): RecyclerView.Adapter<RVAdapterKotlin.ItemViewHolder>() {

    var KotlinList = emptyList<KotlinDetails>()

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
        var Topic = KotlinList[position]
        holder.itemView.apply {
            tvTitle.text = Topic.Title
            tvDiscrption.text = Topic.Description
            cvMain.setOnClickListener {
                ShowAlert(activity , Topic.Title , Topic.MoreDetails)
            }
            ibDelete.setOnClickListener {
                activity.DeleteAlert(Topic)
            }
            ibUpdate.setOnClickListener {
                activity.UpdateAlert(Topic)
            }
        }
    }

    override fun getItemCount() = KotlinList.size

    fun Update(KotlinList: List<KotlinDetails>){
        this.KotlinList = KotlinList
        notifyDataSetChanged()
    }
}