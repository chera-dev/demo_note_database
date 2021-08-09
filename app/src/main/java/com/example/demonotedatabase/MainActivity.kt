package com.example.demonotedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demonotedatabase.Notes.Companion.NOTES
import com.example.demonotedatabase.Notes.Companion.PINNED

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mUserViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mUserViewModel.deleteAllNotes()
        var list: List<Notes>? = mUserViewModel.allNotes.value
        val note = Notes("chera","nothing", NOTES)
        mUserViewModel.addNote(note)
        note.noteDetails = "something"
        mUserViewModel.updateNote(note)
        mUserViewModel.addLabel("nooo")
        val note2 = Notes("dev","xxx",NOTES)
        note2.pinned = PINNED
        mUserViewModel.addNote(note2)
        val note3 = Notes("cherry","mmm", NOTES)
        //don't use below too
        note3.addLabel("yess")
        note3.pinned = PINNED
        mUserViewModel.addNote(note3)
        //***don't use this
        //label.addNote(note3)
        //label.addNote(note)


        //note3.labels.add(label)
        //not working
        /*list?.get(0).let {
            if (it != null) {
                mUserViewModel.delete(it)
                Toast.makeText(this,"del",Toast.LENGTH_LONG).show()
            }
        }
        mUserViewModel.delete(note2)*/

        val adapter2 = NoteAdapter()
        mUserViewModel.allNotes.observe(this, {
            list = it
            adapter2.setNotes(it)
        })
        val notesRecyclerView2 = findViewById<RecyclerView>(R.id.notes_recycler_view2)
        notesRecyclerView2.adapter = adapter2
        notesRecyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        var labelList = mUserViewModel.allLabels.value
        mUserViewModel.addLabel("no")
        val labelAdapter = LabelAdapter()
        mUserViewModel.allLabels.observe(this, {
            labelList = it
            labelAdapter.setNotes(it)
        })
        val labelRecyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view_label)
        labelRecyclerView.adapter = labelAdapter
        labelRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        mUserViewModel.addLabel("no")
        findViewById<Button>(R.id.button).setOnClickListener {
            mUserViewModel.addLabelWithNote(list!![0], labelList!![0])
            mUserViewModel.addLabelWithNote(list!![1], labelList!![0])
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val notes = list!![0]
            notes.noteDetails = "extra"
            mUserViewModel.updateNote(notes)
            val notesOfLabel = mUserViewModel.getNotesOfNoteIds(labelList!![0].getNotes())
            Toast.makeText(this, notesOfLabel.toString(),Toast.LENGTH_LONG).show()
            //val noteById = mUserViewModel.getNotesOfNoteIds(setOf<Long>(list!![0].noteId,list!![1].noteId))
            //Toast.makeText(this, noteById.value?.size.toString(),Toast.LENGTH_LONG).show()
        }

    }
}