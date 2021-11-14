package com.example.imagesapp.RVAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesapp.Data.ImagesDetails
import com.example.imagesapp.Favorites
import com.example.imagesapp.R
import com.example.imagesapp.myViewModel
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.item_row2.view.*

class RVAdapterFavorites(val fragment: Favorites) : RecyclerView.Adapter<RVAdapterFavorites.ItemViewHolder>() {

    var ImageList = emptyList<ImagesDetails>()
    val myViewFav by lazy { ViewModelProvider(fragment).get(myViewModel::class.java) }
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row2,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val Image = ImageList[position]
        holder.itemView.apply {
            Glide.with(fragment).load(Image.Link).into(ivImageFav)
            tvNameFav.text = Image.Title
            ibDelete.setOnClickListener {
                myViewFav.deleteImages(Image)
            }
        }
    }

    override fun getItemCount() = ImageList.count()

    fun update(ImageList : List<ImagesDetails>){
        this.ImageList = ImageList
        notifyDataSetChanged()
    }

}