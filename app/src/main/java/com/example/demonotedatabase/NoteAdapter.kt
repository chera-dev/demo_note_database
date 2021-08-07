package com.example.demonotedatabase

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demonotedatabase.Notes.Companion.PINNED
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NoteAdapter  ()
    : RecyclerView.Adapter<NoteAdapter.SummaNoteCardViewHolder>() {

    private lateinit var context: Context
    private var notesList = mutableListOf<Notes>()
    fun setNotes(list:List<Notes>){
        notesList = list as MutableList<Notes>
        notifyDataSetChanged()
    }
    inner class SummaNoteCardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemTitle: TextView = view.findViewById(R.id.item_title)
        val itemDetails: TextView = view.findViewById(R.id.item_details)
        val itemDate: TextView = view.findViewById(R.id.item_date)
        val itemTime: TextView = view.findViewById(R.id.item_time)
        val chipGroup: ChipGroup = view.findViewById(R.id.chip_group)
        val labelTag: TextView = view.findViewById(R.id.label_tag)
        val itemPinned: TextView = view.findViewById(R.id.item_pinned)

        init {
            context = view.context
            view.setOnClickListener {
                Toast.makeText(view.context,"summa", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaNoteCardViewHolder {
        return SummaNoteCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    private fun ChipGroup.addChip(context: Context, label:String){
        Chip(context).apply {
            text = label
            addView(this)
        }
    }

    override fun onBindViewHolder(holder: SummaNoteCardViewHolder, position: Int) {

        val data: Notes = notesList[position]
        holder.itemTitle.text = data.noteTitle
        holder.itemDetails.text = data.noteDetails
        if(data.pinned == PINNED){
            holder.itemPinned.text = "Pinned"
        }
        val labels = data.labels
        if (labels.isNotEmpty()) {
            Toast.makeText(context,"labels rec",Toast.LENGTH_LONG).show()
            for (i in labels) {
                holder.chipGroup.addChip(holder.chipGroup.context,i.labelName)
            }
        }
    }
}