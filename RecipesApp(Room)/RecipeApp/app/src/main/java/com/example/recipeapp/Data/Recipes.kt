package com.example.recipeapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "Title") var Title: String,
    @ColumnInfo(name = "Author") var Author: String,
    @ColumnInfo(name = "Ingredients") var Ingredients: String,
    @ColumnInfo(name = "Instructions") var Instructions: String
)