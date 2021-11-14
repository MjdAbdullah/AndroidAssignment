package com.example.imagesapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImagesDetails")
data class ImagesDetails(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "ID")val ID : Int,
    @ColumnInfo(name = "Title") val Title: String,
    @ColumnInfo(name = "Link") val Link: String
)