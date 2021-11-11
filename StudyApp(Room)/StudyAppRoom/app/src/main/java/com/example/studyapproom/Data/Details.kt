package com.example.studyapproom.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KotlinDetails" )
data class KotlinDetails (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "Title") var Title: String,
    @ColumnInfo(name = "Description") var Description: String,
    @ColumnInfo(name = "MoreDetails") var MoreDetails: String
        )
@Entity(tableName = "AndroidDetails")
data class AndroidDetails(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "Title") var Title: String,
    @ColumnInfo(name = "Description") var Description: String,
    @ColumnInfo(name = "MoreDetails") var MoreDetails: String
)