package com.example.demonotedatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Notes>>
    private val repository: NoteRepository

    init {
        val userDao = NoteDatabase.getDatabase(
            application
        ).noteDao()
        repository = NoteRepository(userDao)
        readAllData = repository.readAllNotes
    }

    fun addUser(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateUser(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteUser(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

}