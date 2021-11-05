package com.example.noteapp_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyVM(application : Application):AndroidViewModel(application) {
    private val repository: NoteRepository
    private val note: LiveData<List<Note>>
    init {
        val noteDao = NoteDatabase.getinstance(application).NoteDao()
        repository = NoteRepository(noteDao)
        note = repository.getNotes
    }

    fun getNotes(): LiveData<List<Note>>{
        return note as LiveData<List<Note>>
    }

    fun addNote(noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(Note(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(Note(noteID,noteText))
        }
    }

    fun deleteNote(noteID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(Note(noteID,""))
        }
    }

}