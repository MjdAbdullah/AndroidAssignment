package com.example.guessthephrasesqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details.db",null,1){

    var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("create table Phrase(phrase text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }
    // save data in the database
    fun putData(S1 : String): Long {
        val cv = ContentValues()
        cv.put("phrase",S1)
        var state = sqLiteDatabase.insert("Phrase",null,cv)
        return state
    }
    // read data in the database
    fun getData(): ArrayList<String> {
        val arr = ArrayList<String>()
        val SelectQuery = "SELECT * FROM Phrase"
        val DB = this.readableDatabase
        var c : Cursor? = null
        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }

        var phrase : String
        if(c.moveToFirst()){
            do {
                phrase = c.getString(c.getColumnIndex("phrase"))
                arr.add(phrase)
            }while (c.moveToNext())
        }
        return arr
    }
}