package com.example.headsup2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details.db",null,1){

    var sqLiteDatabase : SQLiteDatabase = writableDatabase
    companion object{
        private var ID_key = "ID"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Celebrity($ID_key INTEGER PRIMARY KEY,Name text, Taboo1 text, Taboo2 text, Taboo3 text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }

    fun SaveValues(Name: String, T1: String, T2: String, T3: String): Long {
        val cv = ContentValues()
        cv.put("Name",Name)
        cv.put("Taboo1",T1)
        cv.put("Taboo2",T2)
        cv.put("Taboo3",T3)
        var state = sqLiteDatabase.insert("Celebrity",null,cv)
        return state
    }
}