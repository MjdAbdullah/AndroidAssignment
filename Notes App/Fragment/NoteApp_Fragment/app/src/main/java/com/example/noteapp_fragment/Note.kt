package com.example.noteapp_fragment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val Id : Int,
    @ColumnInfo(name = "NoteText") var NoteText : String
)
