package com.example.notesapp

data class NoteDetails(
    var Id: Int,
    var Note: String
){
    @JvmName("setNote1")
    fun setNote(new: String){
        this.Note = new
    }
}
