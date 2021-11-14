package com.example.imagesapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImagesDetails::class], version = 1, exportSchema = false)
abstract class ImagesDatabase : RoomDatabase(){
    companion object{
        var INT: ImagesDatabase? = null
        fun getinstance(cx: Context): ImagesDatabase {
            if(INT != null){
                return INT as ImagesDatabase
            }
            INT = Room.databaseBuilder(
                cx,
                ImagesDatabase::class.java,
                "ImagesDatabase"
            ).run { allowMainThreadQueries() }.build();
            return INT as ImagesDatabase
        }
    }
    abstract fun ImagesDao(): ImagesDao
}