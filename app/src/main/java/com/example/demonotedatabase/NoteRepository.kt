package com.example.demonotedatabase

import android.app.Application
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {

    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Notes>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Notes) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Notes) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(noteDao).execute(note)
    }


    fun delete(note: Notes) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return allNotes
    }

    companion object {
        private class InsertNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Notes, Unit, Unit>() {

            override fun doInBackground(vararg p0: Notes?) {
                noteDao.insert(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Notes, Unit, Unit>() {

            override fun doInBackground(vararg p0: Notes?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Notes, Unit, Unit>() {

            override fun doInBackground(vararg p0: Notes?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllNotes()
            }
        }
    }
}