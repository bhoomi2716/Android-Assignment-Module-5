package com.example.assignment_module5

import android.app.Activity
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.assignment_module5.NotesAdapter.Companion.selectedPosition

class NotesAdapter(var context: Context, var noteslist : MutableList<NotesDetails>) : RecyclerView.Adapter<NotesView>()
{
    lateinit var dbObject : NotesDatabase

    companion object {
        var selectedPosition: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesView
    {
        var notesinflater = LayoutInflater.from(parent.context)
        var notesview = notesinflater.inflate(R.layout.viewnotes_design,parent,false)
        dbObject = Room.databaseBuilder(parent.context,NotesDatabase::class.java,"Notes_Database")
            .allowMainThreadQueries()
            .build()

        return NotesView(notesview)
    }

    override fun getItemCount(): Int
    {
        return noteslist.size
    }

    override fun onBindViewHolder(holder: NotesView, position: Int)
    {
        holder.noteDate.setText(noteslist.get(position).NoteDate)
        holder.noteHeading.setText(noteslist.get(position).NoteHeading)
        holder.noteDescription.setText(noteslist.get(position).NoteDescription)

    }

}

class NotesView(notes : View) : RecyclerView.ViewHolder(notes), View.OnCreateContextMenuListener
{
    lateinit var noteDate : TextView
    lateinit var noteHeading : TextView
    lateinit var noteDescription : TextView

    init {

        noteDate = notes.findViewById(R.id.VIEW_date)
        noteHeading = notes.findViewById(R.id.VIEW_heading)
        noteDescription = notes.findViewById(R.id.VIEW_description)
        notes.setOnCreateContextMenuListener(this)
        notes.setOnLongClickListener{
            selectedPosition = adapterPosition
            false
        }

    }

    override fun onCreateContextMenu(p0: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?)
    {
       var inflater = (p1!!.context as Activity).menuInflater
       inflater.inflate(R.menu.ud_menuitem,p0)
    }

}