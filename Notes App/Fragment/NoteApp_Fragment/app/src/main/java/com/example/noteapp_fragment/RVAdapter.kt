package com.example.noteapp_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val home: Home) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    private var notes = emptyList<Note>()

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
        val data = notes[position]
        holder.itemView.apply {
            tvText.text = data.NoteText
            ivDelete.setOnClickListener {
                //myViewModel.deleteNote(data.Id)
                home.myVM.deleteNote(data.Id,)

            }

            ivUpdate.setOnClickListener {
                with(home.SP.edit()){
                    putInt("NoteId", data.Id)
                    apply()
                }
                home.findNavController().navigate(R.id.action_home2_to_updateNote)
            }
        }
    }

    override fun getItemCount() = notes.count()

    fun update(arr: List<Note>) {
        this.notes = arr
        notifyDataSetChanged()
    }
}