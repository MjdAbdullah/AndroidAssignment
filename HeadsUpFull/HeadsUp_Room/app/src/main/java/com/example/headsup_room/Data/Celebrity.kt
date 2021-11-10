package com.example.headsup_room.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Celebrity")
data class Celebrity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val Id: Int,
    @ColumnInfo(name = "Name")var Name: String,
    @ColumnInfo(name = "Taboo1")var Taboo1: String,
    @ColumnInfo(name = "Taboo2")var Taboo2: String,
    @ColumnInfo(name = "Taboo3")var Taboo3: String
)
