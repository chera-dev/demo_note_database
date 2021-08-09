package com.example.demonotedatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.demonotedatabase.Notes.Companion.NOTES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Notes>>
    private val noteRepository: NoteRepository
    private val labelRepository: LabelRepository
    val allLabels: LiveData<List<Label>>

    private var nextLabelOrder = 1

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()

    val baseNotes = Content(noteDao.getBaseNotes(NOTES))

    init {
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.readAllNotes
        //notes = noteRepository.getNotes

        val labelDao = NoteDatabase.getDatabase(
            application
        ).labelDao()
        labelRepository = LabelRepository(labelDao)
        allLabels = labelRepository.readAllLabel

    }


    fun addNote(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note)
        }
    }

    fun updateNote(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAllNotes()
        }
    }

    fun getNotesOfNoteIds(noteIds: Set<Long>): List<Notes>? {
        return allNotes.value?.filter { notes: Notes -> noteIds.contains(notes.noteId) }
        //return Content(noteDao.getNotesOfNoteIds(noteIds))
    }

    fun addLabelWithNote(note: Notes,label: Label){
        note.addLabel(label.labelName)
        updateNote(note)
        label.addNote(note)
        updateLabel(label)
    }

    fun getNoteById(noteId:Long):Notes?{
        var note:Notes? = null
        viewModelScope.launch(Dispatchers.IO) {
            note = noteDao.getNoteById(noteId)
            note = Notes("sfs","sgfw",NOTES)
        }
        return note
    }


    fun addLabel(labelName:String){
        val label = Label(labelName)
        label.order = nextLabelOrder++
        viewModelScope.launch(Dispatchers.IO) {
           labelRepository.addLabel(label)
        }
    }

    fun updateLabel(label: Label){
        viewModelScope.launch(Dispatchers.IO) {
            labelRepository.updateLabel(label)
        }
    }

    fun deleteLabel(label: Label){
        viewModelScope.launch(Dispatchers.IO) {
            labelRepository.deleteLabel(label)
        }
    }


}

class Content<T>(liveData: LiveData<List<T>>) : LiveData<List<T>>() {

    private val observer = Observer<List<T>> { list -> value = list }

    init {
        liveData.observeForever(observer)
    }
}
