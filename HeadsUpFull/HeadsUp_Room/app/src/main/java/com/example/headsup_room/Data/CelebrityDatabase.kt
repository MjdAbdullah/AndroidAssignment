package com.example.headsup_room.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Celebrity::class], version = 1, exportSchema = false)
abstract class CelebrityDatabase : RoomDatabase() {
    companion object{
        var INT: CelebrityDatabase? = null
        fun getinstance(cx: Context): CelebrityDatabase {
            if(INT != null){
                return INT as CelebrityDatabase
            }
            INT = Room.databaseBuilder(
                cx,
                CelebrityDatabase::class.java,
                "Celebrity_Database"
            ).run { allowMainThreadQueries() }.build();
            return INT as CelebrityDatabase
        }
    }
    abstract fun CelebrityDao(): CelebrityDao
}