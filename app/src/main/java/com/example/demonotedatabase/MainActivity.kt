package com.example.demonotedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        mUserViewModel.deleteAllUsers()
        var list: List<Notes>? = mUserViewModel.readAllData.value
        val label = Label(1,"nooo")
        val note = Notes("chera","nothing", NOTES, setOf(label,label))
        mUserViewModel.addUser(note)
        note.noteDetails = "something"
        mUserViewModel.updateUser(note)
        val note2 = Notes("dev","xxx",NOTES, setOf(label))
        note2.pinned = PINNED
        mUserViewModel.addUser(note2)
        val note3 = Notes("cherry","mmm", NOTES, setOf(label))
        note3.pinned = PINNED
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
        mUserViewModel.readAllData.observe(this, {
            list = it
            adapter2.setNotes(it)
        })
        val notesRecyclerView2 = findViewById<RecyclerView>(R.id.notes_recycler_view2)
        notesRecyclerView2.adapter = adapter2
        notesRecyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}