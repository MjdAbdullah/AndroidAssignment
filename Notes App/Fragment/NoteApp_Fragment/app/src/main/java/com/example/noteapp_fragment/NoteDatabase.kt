package com.example.noteapp_fragment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    companion object{
        var INT: NoteDatabase? = null
        fun getinstance(cx: Context): NoteDatabase {
            if(INT != null){
                return INT as NoteDatabase
            }
            INT = Room.databaseBuilder(
                cx,
                NoteDatabase::class.java,
                "NoteDatabase"
            ).run { allowMainThreadQueries() }.build();
            return INT as NoteDatabase
        }
    }
    abstract fun NoteDao(): NoteDao
}