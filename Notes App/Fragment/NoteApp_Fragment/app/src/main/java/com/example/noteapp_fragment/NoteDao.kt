package com.example.noteapp_fragment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note ORDER BY Id ASC")
    fun getNote(): LiveData<List<Note>>

    @Insert
    fun addNote(newNote: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)
}