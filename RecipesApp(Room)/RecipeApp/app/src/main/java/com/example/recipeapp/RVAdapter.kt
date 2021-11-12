package com.example.recipeapp

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.Data.Recipes
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val mainFragment: Main_Fragment) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var RecipesList = emptyList<Recipes>()
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
        val RecipeDetails = RecipesList[position]
        holder.itemView.apply {
            tvTitle.text = "${RecipeDetails.Title}"
            tvAuthor.text = "Author: ${RecipeDetails.Author}"
            tvIngredients.text = "Ingredients: ${RecipeDetails.Ingredients}"
            tvInstructions.text = "Instructions: ${RecipeDetails.Instructions}"
            ibUpdate.setOnClickListener {
                mainFragment.updateFragment(RecipeDetails)
            }
            ibDelete.setOnClickListener {
                mainFragment.myViewRecipes.deleteRecipe(RecipeDetails)
            }
        }
    }

    override fun getItemCount() = RecipesList.count()

    fun update(RecipesList: List<Recipes>){
        this.RecipesList = RecipesList
        notifyDataSetChanged()
    }
}