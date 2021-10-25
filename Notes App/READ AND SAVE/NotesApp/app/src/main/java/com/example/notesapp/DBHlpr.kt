package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
    // save data in the database
    fun putData(S1 : String): Long {
        val cv = ContentValues()
        cv.put("note",S1)
        var state = sqLiteDatabase.insert("Note",null,cv)
        return state
    }
    // read data in the database
    fun getData(): ArrayList<String> {
        val arr = ArrayList<String>()
        val SelectQuery = "SELECT * FROM Note"
        val DB = this.readableDatabase
        var c : Cursor? = null
        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }

        var note : String
        if(c.moveToFirst()){
            do {
                note = c.getString(c.getColumnIndex("note"))
                arr.add(note)
            }while (c.moveToNext())
        }
        return arr
    }
}