package com.example.studyapp.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context): SQLiteOpenHelper(context,"details.db",null,1){

    companion object{
        private val Kotlin_Table = "KOTLIN"
        private val Android_Table = "ANDROID"
        private var ID_key = "ID"
        private var Title_Col = "Title"
        private var Description_Col = "Description"
        private var Details_Col = "Details"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $Android_Table($ID_key INTEGER PRIMARY KEY, $Title_Col text, $Description_Col text, $Details_Col text)")
        p0?.execSQL("create table $Kotlin_Table($ID_key INTEGER PRIMARY KEY, $Title_Col text, $Description_Col text, $Details_Col text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }
    //----------------------------------------------------------------------------------------------
    // Add Date to the Tables
    fun AddKotlinData(new : Details): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("$Title_Col",new.Title)
        cv.put("$Description_Col",new.Description)
        cv.put("$Details_Col",new.Details)
        var state = db.insert("$Kotlin_Table",null,cv)
        db.close()
        return state
    }

    fun AddAndroidDate(new : Details): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("$Title_Col",new.Title)
        cv.put("$Description_Col",new.Description)
        cv.put("$Details_Col",new.Details)
        var state = db.insert("$Android_Table",null,cv)
        db.close()
        return state
    }
    //----------------------------------------------------------------------------------------------
    // Get data from Tables
    // read data in the database from Android Table
    fun getAndroidData(): ArrayList<Details> {

        val AndeoidArray = ArrayList<Details>()
        val SelectQuery = "SELECT * FROM $Android_Table"
        val DB = this.readableDatabase
        var c : Cursor? = null
        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }
        var ID: Int
        var titel: String
        var dec: String
        var det: String
        if(c.moveToFirst()){
            do {
                ID = c.getInt(c.getColumnIndex(ID_key))
                titel = c.getString(c.getColumnIndex("$Title_Col"))
                dec = c.getString(c.getColumnIndex("$Description_Col"))
                det = c.getString(c.getColumnIndex("$Details_Col"))
                AndeoidArray.add(Details(ID,titel,dec, det))
            }while (c.moveToNext())
        }
        DB.close()
        return AndeoidArray
    }

    // read data in the database from Kotlin Table
    fun getKotlinData(): ArrayList<Details> {

        val KotlinArray = ArrayList<Details>()
        val SelectQuery = "SELECT * FROM $Kotlin_Table"
        val DB = this.readableDatabase
        var c : Cursor? = null
        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }
        var ID: Int
        var titel: String
        var dec: String
        var det: String
        if(c.moveToFirst()){
            do {
                ID = c.getInt(c.getColumnIndex("$ID_key"))
                titel = c.getString(c.getColumnIndex("$Title_Col"))
                dec = c.getString(c.getColumnIndex("$Description_Col"))
                det = c.getString(c.getColumnIndex("$Details_Col"))
                KotlinArray.add(Details(ID,titel,dec, det))
            }while (c.moveToNext())
        }
        DB.close()
        return KotlinArray
    }
    //----------------------------------------------------------------------------------------------
    // Delete Delete from the Tables .
    fun deleteAndroidData(selectData: Details): Int {
        val db = this.writableDatabase
        val success = db.delete("$Android_Table", "$ID_key ="+selectData.ID,null)
        db.close()
        return success
    }

    fun deleteKotlinData(SelectData: Details): Int {
        val db = this.writableDatabase
        val success = db.delete("$Kotlin_Table", "$ID_key ="+SelectData.ID,null)
        db.close()
        return success
    }
    //----------------------------------------------------------------------------------------------
    // Update Tables Data .
    fun updateAndroidDate(new: Details): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("$Title_Col",new.Title)
        cv.put("$Description_Col",new.Description)
        cv.put("$Details_Col",new.Details)
        val success = db.update("$Android_Table", cv,"id ="+new.ID,null)
        db.close()
        return success
    }

    fun updateKotlinDate(new: Details): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("$Title_Col",new.Title)
        cv.put("$Description_Col",new.Description)
        cv.put("$Details_Col",new.Details)
        val success = db.update("$Kotlin_Table", cv,"id ="+new.ID,null)
        db.close()
        return success
    }
}