package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details.db",null,1){

    var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Note(note text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }

    fun SaveValues(S1 : String): Long {
        val cv = ContentValues()
        cv.put("note",S1)
        var state = sqLiteDatabase.insert("Note",null,cv)
        return state
    }
}