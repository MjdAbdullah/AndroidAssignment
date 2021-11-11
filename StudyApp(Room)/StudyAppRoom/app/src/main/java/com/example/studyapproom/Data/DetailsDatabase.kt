package com.example.studyapproom.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AndroidDetails::class,KotlinDetails::class],version = 1,exportSchema = false)
abstract class DetailsDatabase : RoomDatabase(){
    companion object{
        var INT: DetailsDatabase? = null
        fun getinstance(cx: Context): DetailsDatabase {
            if(INT != null){
                return INT as DetailsDatabase
            }
            INT = Room.databaseBuilder(
                cx,
                DetailsDatabase::class.java,
                "AndroidAndKotlinDetails_Database"
            ).run { allowMainThreadQueries() }.build();
            return INT as DetailsDatabase
        }
    }
    abstract fun DetailsDao(): DetailsDao
}