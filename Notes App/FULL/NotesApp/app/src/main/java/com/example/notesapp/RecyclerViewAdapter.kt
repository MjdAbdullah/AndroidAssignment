package com.example.notesapp

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(val activity: MainActivity , val context: Context, val arr: ArrayList<NoteDetails>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
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
        val DB = DBHlpr(context)
        holder.itemView.apply {
            tvView.text = data.Note
            ibDelete.setOnClickListener{
                DB.deleteData(data)
                activity.update(DB)
            }
            ibUpdate.setOnClickListener{
                update(DB,data)
            }
        }
    }

    override fun getItemCount() = arr.count()

    fun update(DB: DBHlpr, data: NoteDetails){
        val alert = AlertDialog.Builder(context)
        val L = LinearLayout(context)
        val etText = EditText(context)
        L.orientation = LinearLayout.VERTICAL
        L.addView(etText)
        alert.setTitle("Alert Message")
        alert.setView(L)
            .setPositiveButton("SAVE"){
                    dialog,which -> data.setNote(etText.text.toString()); DB.updateData(data); activity.update(DB)

            }
            .setNegativeButton("CANCEL"){
                    dialog,which -> dialog.cancel()
            }
            .show()
    }
}