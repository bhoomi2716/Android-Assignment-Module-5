package com.example.assignment_module5

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import java.util.Calendar

class MainActivity : AppCompatActivity()
{
    lateinit var NotesView : RecyclerView
    lateinit var NotesItems : MutableList<NotesDetails>
    lateinit var addBtn : Button
    lateinit var dbObject : NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotesView = findViewById(R.id.recyclerview)
        NotesItems = ArrayList()
        addBtn = findViewById(R.id.addNoteBtn)

        dbObject = Room.databaseBuilder(applicationContext,NotesDatabase::class.java,"Notes_Database")
            .allowMainThreadQueries()
            .build()


        NotesItems = dbObject.NotesDeo().ViewNotes()

        var notesAdapter = NotesAdapter(applicationContext,NotesItems)
        NotesView.adapter = notesAdapter

        registerForContextMenu(NotesView)


        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        NotesView.layoutManager = layoutManager

        addBtn.setOnClickListener {

            startActivity(Intent(applicationContext,AddNotesActivity::class.java))
        }



    }

    fun DeleteNotes(Id : Int)
    {
        var deleteNote = NotesDetails()
        deleteNote.NoteID = Id
        dbObject.NotesDeo().DeleteNotes(deleteNote)
        Toast.makeText(applicationContext,"Note Deleted", Toast.LENGTH_SHORT).show()
    }


    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        var position = NotesAdapter.selectedPosition
        if (position == -1) return super.onContextItemSelected(item)
        var selectedNote = NotesItems[position]

        when(item.itemId)
        {
            R.id.update->
            {
                var updateIntent = Intent(applicationContext,UpdateNotesActivity::class.java)
                updateIntent.putExtra("ID",selectedNote.NoteID)
                updateIntent.putExtra("Date",selectedNote.NoteDate)
                updateIntent.putExtra("Heading",selectedNote.NoteHeading)
                updateIntent.putExtra("Description",selectedNote.NoteDescription)
                startActivity(updateIntent)
            }
        }

        when(item.itemId)
        {
            R.id.delete->
            {
                var deleteDialog = AlertDialog.Builder(this)
                deleteDialog.setTitle("Are You Sure Want To Delete This Note ???")
                deleteDialog.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

                    DeleteNotes(selectedNote.NoteID)
                    startActivity(Intent(applicationContext,MainActivity::class.java))

                })
                deleteDialog.setNegativeButton("NO", { dialogInterface: DialogInterface, i: Int ->

                    dialogInterface.cancel()

                })

                deleteDialog.show()
            }
        }
        return super.onContextItemSelected(item)
    }
}

