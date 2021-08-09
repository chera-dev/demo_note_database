package com.example.demonotedatabase


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note:Notes)

    @Update
    suspend fun updateNote(note:Notes)

    @Delete
    suspend fun deleteNote(note:Notes)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY note_id DESC")
    fun readAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM note_table WHERE note_id = :noteId LIMIT 1")
    suspend fun getNoteById(noteId:Long): Notes?

    //okay
    @Query("SELECT * FROM note_table WHERE note_type=:noteType")
    fun getBaseNotes(noteType:Int):LiveData<List<Notes>>

    //
    @Query("SELECT * FROM note_table WHERE note_id IN (:noteIds)")
    fun getNotesOfNoteIds(noteIds: Set<Long>):LiveData<List<Notes>>



    //not
    //@Query("SELECT * FROM note_table WHERE :label IN (labels)")
    //suspend fun getBaseNotesByLabel(label: String): LiveData<List<Notes>>


}