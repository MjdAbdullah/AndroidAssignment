package com.example.recipeapp.Data

import androidx.lifecycle.LiveData

class RecipesRepository(private val recipesDao: RecipesDao ) {

    val getRecipes: LiveData<List<Recipes>> = recipesDao.getRecipes()

    suspend fun addRecipe(newRecipe: Recipes){
        recipesDao.addRecipe(newRecipe)
    }

    suspend fun updateRecipe(selectRecipe: Recipes){
        recipesDao.updateRecipe(selectRecipe)
    }

    suspend fun deleteRecipe(selectRecipe: Recipes){
        recipesDao.deleteRecipe(selectRecipe)
    }

}