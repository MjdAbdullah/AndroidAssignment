package com.example.studyapp.RVAdapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.Android
import com.example.studyapp.Data.Details
import com.example.studyapp.Kotlin
import com.example.studyapp.R
import com.example.studyapp.ShowAlert
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapterAndroid(val activity: Android, var AndroidList : ArrayList<Details> ): RecyclerView.Adapter<RVAdapterAndroid.ItemViewHolder>() {
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
                ShowAlert(activity , Topic.Title , Topic.Details)
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

    fun Update(AndroidList : ArrayList<Details>){
        this.AndroidList = AndroidList
        notifyDataSetChanged()
    }
}