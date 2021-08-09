package com.example.demonotedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class LabelAdapter() : RecyclerView.Adapter<LabelAdapter.SummaNoteCardViewHolder>() {

    private lateinit var context: Context
    private var notesList = mutableListOf<Label>()
    fun setNotes(list:List<Label>){
        notesList = list as MutableList<Label>
        notifyDataSetChanged()
    }
    inner class SummaNoteCardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemTitle: TextView = view.findViewById(R.id.textViewLabel)
        val noteTitle: TextView = view.findViewById(R.id.textViewId)

        init {
            context = view.context
            view.setOnClickListener {
                Toast.makeText(view.context,"summa", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaNoteCardViewHolder {
        return SummaNoteCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.label_card_view, parent, false))
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

        val data: Label = notesList[position]
        holder.itemTitle.text = data.labelName
        holder.noteTitle.text = data.getNotes().toString()
        /*val labels = data.labels
        if (labels.isNotEmpty()) {
            Toast.makeText(context,"labels rec", Toast.LENGTH_LONG).show()
            for (i in labels) {
                holder.chipGroup.addChip(holder.chipGroup.context,i.labelName)
            }
        }*/
    }
}