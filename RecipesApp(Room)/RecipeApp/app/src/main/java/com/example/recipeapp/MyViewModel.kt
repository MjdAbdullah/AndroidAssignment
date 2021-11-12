package com.example.recipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipeapp.Data.Recipes
import com.example.recipeapp.Data.RecipesDatabase
import com.example.recipeapp.Data.RecipesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RecipesRepository
    private val RecipesDetails: LiveData<List<Recipes>>

    init {
        val detailsDao = RecipesDatabase.getinstance(application).RecipesDao()
        repository = RecipesRepository(detailsDao)
        RecipesDetails = repository.getRecipes
    }

    fun getRecipes(): LiveData<List<Recipes>>{
        return RecipesDetails
    }

    fun addRecipe(newRecipe: Recipes){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addRecipe(newRecipe)
        }
    }

    fun updateRecipe(selectRecipe: Recipes){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateRecipe(selectRecipe)
        }
    }

    fun deleteRecipe(selectRecipe: Recipes){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteRecipe(selectRecipe)
        }
    }


}