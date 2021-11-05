package com.example.noteapp_fragment

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val getNotes: LiveData<List<Note>> = noteDao.getNote()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}