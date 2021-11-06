package com.example.debuggingchallenge3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.debuggingchallenge3.databinding.ItemRowBinding

class RVAdapter (private var definitions: ArrayList<ArrayList<String>>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val definition = definitions[position]

        holder.binding.apply {
            tvWord.text = definition[0]
            tvDefinition.text = definition[1]
        }
    }

    override fun getItemCount() = definitions.size

    fun update(){
        notifyDataSetChanged()
    }
}