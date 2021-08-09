package com.example.demonotedatabase

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.example.demonotedatabase.Notes.Companion.NOTES

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Notes>> = noteDao.readAllNotes()
    //val getNotes: List<Notes> = noteDao.getNotes()

    suspend fun addNote(note:Notes){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note:Notes){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note:Notes){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }

    /*fun getBaseNotesByLabel(labelName:String):LiveData<List<Notes>>{
        return noteDao.getBaseNotesByLabel(labelName)
    }*/


    /*fun getNotes(): LiveData<List<Notes>> {
        return noteDao.getAllNotes()
    }*/

}