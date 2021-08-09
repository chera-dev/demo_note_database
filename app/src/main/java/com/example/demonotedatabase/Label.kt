package com.example.demonotedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson


@Entity(tableName = "label_table")
data class Label(@PrimaryKey @ColumnInfo(name = "label_name") var labelName:String,
                 @ColumnInfo(name = "notes") private val notes: MutableSet<Notes> = mutableSetOf()){

    @ColumnInfo(name = "order") var order:Int = 0

    fun addNote(noteId:Notes){
        notes.add(noteId)
    }

    fun removeNotes(note:Notes){
        notes.remove(note)
    }

    fun getNotes():Set<Notes>{
        return notes
    }

    fun getNotesId():Set<Long>{
        val notes = mutableSetOf<Long>()
        for (i in this.notes)
            notes.add(i.noteId)
        return notes
    }

}


class NoteTypeConverter {

    @TypeConverter
    fun listToJson(value: MutableSet<Notes>?): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Notes>::class.java).toMutableSet()
}

