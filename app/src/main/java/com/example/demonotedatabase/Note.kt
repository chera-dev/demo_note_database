package com.example.demonotedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "note_table")
data class Notes(@ColumnInfo(name = "note_title") var noteTitle:String,
                 @ColumnInfo(name = "note_details") var noteDetails:String,
                 @ColumnInfo(name = "note_type") var noteType: Int,
                 //@ColumnInfo(name = "labels") val labels: List<Label>
                 ){

    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0

    @ColumnInfo(name = "note_pinned")
    var pinned:Int = UNPINNED


/*
    val timeCreated: String
        get() = getCurrentTimeAndDate().first

    val dateCreated: String
        get() = getCurrentTimeAndDate().second


    private fun getCurrentTimeAndDate(): Pair<String,String>{
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val timeCreated: String = currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        val dateCreated: String = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        return Pair(timeCreated,dateCreated)
    }*/

    companion object{
        const val NOTES = 1
        const val ARCHIVED = 2
        const val PINNED = 1
        const val UNPINNED = 0
    }
}

data class Label(val labelId:Int,var labelName:String)


class LabelTypeConverter {

    @TypeConverter
    fun listToJson(value: List<Label>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Label>::class.java).toList()
}