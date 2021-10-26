package com.example.signup_signin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details.db",null,1){

    var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("create table Users(Name text, Email text, Phone text, Password text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }

    // save data in the database
    fun putData(data: UserDetails): Long {

        val cv = ContentValues()
        cv.put("Name",data.Name)
        cv.put("Email",data.Email)
        cv.put("Phone",data.Phone)
        cv.put("Password",data.Password)
        var state = sqLiteDatabase.insert("Users",null,cv)
        return state

    }

    // read data in the database
    fun getData(): ArrayList<UserDetails> {

        val arr = ArrayList<UserDetails>()
        val SelectQuery = "SELECT * FROM Users"
        val DB = this.readableDatabase
        var c : Cursor? = null

        try {
            c = DB.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            DB.execSQL(SelectQuery)
            return ArrayList()
        }

        var name : String
        var email : String
        var phone : String
        var password : String

        if(c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex("Name"))
                email = c.getString(c.getColumnIndex("Email"))
                phone = c.getString(c.getColumnIndex("Phone"))
                password = c.getString(c.getColumnIndex("Password"))
                arr.add(UserDetails(name, email, phone, password))
            }while (c.moveToNext())
        }

        return arr
    }

}