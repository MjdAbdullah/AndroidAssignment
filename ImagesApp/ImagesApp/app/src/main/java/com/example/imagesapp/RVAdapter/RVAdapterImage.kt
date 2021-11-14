package com.example.imagesapp.RVAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesapp.Data.ImagesDetails
import com.example.imagesapp.Images
import com.example.imagesapp.R
import com.example.imagesapp.myViewModel
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapterImage(val fragment : Images, var ImageList: ArrayList<ImagesDetails>) : RecyclerView.Adapter<RVAdapterImage.ItemViewHolder>() {

    val myViewImages by lazy { ViewModelProvider(fragment).get(myViewModel::class.java) }
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
        val Image = ImageList[position]
        holder.itemView.apply {
            Glide.with(fragment).load(Image.Link).into(ivImage)
            tvName.text = Image.Title
            ibFavorite.setOnClickListener {
                ibFavorite.setImageResource(R.drawable.ic_baseline_star_24)
                myViewImages.addImages(Image)
            }
        }
    }

    override fun getItemCount() = ImageList.count()

    fun update(ImageList : ArrayList<ImagesDetails>){
        this.ImageList = ImageList
        notifyDataSetChanged()
    }

}