package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details.db",null,1){

    //var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("create table Notes(id INTEGER PRIMARY KEY, note text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        println(p0)
    }
    // save data in the database
    fun putData(new : NoteDetails): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("note",new.Note)
        var state = db.insert("Notes",null,cv)
        db.close()
        return state
    }
    // read data in the database
    fun getData(): ArrayList<NoteDetails> {
        val db = this.readableDatabase
        val arr = ArrayList<NoteDetails>()
        val SelectQuery = "SELECT * FROM Notes"

        var c : Cursor? = null
        try {
            c = db.rawQuery(SelectQuery,null)
        }catch (e:Exception){
            db.execSQL(SelectQuery)
            return ArrayList()
        }

        var id : Int
        var note : String
        if(c.moveToFirst()){
            do {
                id = c.getInt(c.getColumnIndex("id"))
                note = c.getString(c.getColumnIndex("note"))
                arr.add(NoteDetails(id,note))
            }while (c.moveToNext())
        }
        db.close()
        return arr
    }

    //for Update data
    fun updateData(new: NoteDetails): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("note",new.Note)
        val success = db.update("Notes", cv,"id ="+new.Id,null)
        db.close()
        return success
    }

    //for Delete data from database
    fun deleteData(SelectNote: NoteDetails): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("id",SelectNote.Id)
        val success = db.delete("Notes","id ="+SelectNote.Id,null)
        db.close()
        return success
    }
}