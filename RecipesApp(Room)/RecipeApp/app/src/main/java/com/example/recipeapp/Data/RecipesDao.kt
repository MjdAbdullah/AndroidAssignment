package com.example.recipeapp.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipesDao {

    @Query("SELECT * FROM Recipes ORDER BY ID ASC")
    fun getRecipes(): LiveData<List<Recipes>>

    @Insert
    fun addRecipe(newRecipe: Recipes)

    @Update
    fun updateRecipe(Recipe: Recipes)

    @Delete
    fun deleteRecipe(selectRecipe: Recipes)

}