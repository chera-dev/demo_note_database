package com.example.demonotedatabase

import androidx.lifecycle.LiveData

class NoteRepository(private val userDao: NoteDao) {

        val readAllNotes: LiveData<List<Notes>> = userDao.readAllNotes()

        suspend fun addNote(note:Notes){
            userDao.addNote(note)
        }

        suspend fun updateNote(note:Notes){
            userDao.updateNote(note)
        }

        suspend fun deleteNote(note:Notes){
            userDao.deleteNote(note)
        }

        suspend fun deleteAllNotes(){
            userDao.deleteAllNotes()
        }

    }