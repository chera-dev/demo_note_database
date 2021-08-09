package com.example.demonotedatabase

import androidx.room.*
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Entity(tableName = "note_table")
data class Notes(@ColumnInfo(name = "note_title") var noteTitle:String,
                 @ColumnInfo(name = "note_details") var noteDetails:String,
                 @ColumnInfo(name = "note_type") var noteType: Int,
                 @ColumnInfo(name = "labels") private val labels:MutableSet<String> = mutableSetOf()){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Long = 0

    @ColumnInfo(name = "note_pinned")
    var pinned:Int = UNPINNED

    fun addLabel(labelName:String){
        labels.add(labelName)
    }

    fun getLabels():Set<String>{
        return labels
    }

    @Ignore
    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "time_created")
    var timeCreated: String = currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

    @ColumnInfo(name = "date_created")
    var dateCreated: String = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))


    companion object{
        const val NOTES = 1
        const val ARCHIVED = 2
        const val PINNED = 1
        const val UNPINNED = 0
    }
}


class LabelTypeConverter {

    @TypeConverter
    fun listToJson(value: MutableSet<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toMutableSet()
}