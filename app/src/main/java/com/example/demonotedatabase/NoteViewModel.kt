package com.example.demonotedatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Notes>> = repository.getAllNotes()

    fun insert(note: Notes) {
        repository.insert(note)
    }

    fun update(note: Notes) {
        repository.update(note)
    }

    fun delete(note: Notes) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return allNotes
    }
}