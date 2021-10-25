package com.example.headsup2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    // read data in the database
    fun getData(): ArrayList<CelebrityModel> {

        val arr = ArrayList<CelebrityModel>()
        val SelectQuery = "SELECT * FROM Celebrity"
        val DB = this.readableDatabase
        var c : Cursor? = null
        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }

        var Name : String
        var Taboo2 : String
        var Taboo3 : String
        var Taboo1 : String
        if(c.moveToFirst()){
            do {
                Name = c.getString(c.getColumnIndex("Name"))
                Taboo1 = c.getString(c.getColumnIndex("Taboo1"))
                Taboo2 = c.getString(c.getColumnIndex("Taboo2"))
                Taboo3 = c.getString(c.getColumnIndex("Taboo3"))
                arr.add(CelebrityModel(Name,Taboo1, Taboo2, Taboo3))
            }while (c.moveToNext())
        }
        return arr
    }
}