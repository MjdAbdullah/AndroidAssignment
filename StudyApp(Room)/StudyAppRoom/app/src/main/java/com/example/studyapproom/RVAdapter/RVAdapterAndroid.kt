package com.example.studyapproom.RVAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapproom.Android
import com.example.studyapproom.Data.AndroidDetails
import com.example.studyapproom.R
import com.example.studyapproom.ShowAlert
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapterAndroid(val activity: Android): RecyclerView.Adapter<RVAdapterAndroid.ItemViewHolder>() {

    var AndroidList = emptyList<AndroidDetails>()

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
        var Topic = AndroidList[position]
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

    override fun getItemCount() = AndroidList.size

    fun Update(AndroidList: List<AndroidDetails>){
        this.AndroidList = AndroidList
        notifyDataSetChanged()
    }
}