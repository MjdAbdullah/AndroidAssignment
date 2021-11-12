package com.example.recipeapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Recipes::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase(){
    companion object{
        var INT: RecipesDatabase? = null
        fun getinstance(cx: Context): RecipesDatabase {
            if(INT != null){
                return INT as RecipesDatabase
            }
            INT = Room.databaseBuilder(
                cx,
                RecipesDatabase::class.java,
                "RecipesDetails_Database"
            ).run { allowMainThreadQueries() }.build();
            return INT as RecipesDatabase
        }
    }
    abstract fun RecipesDao(): RecipesDao
}