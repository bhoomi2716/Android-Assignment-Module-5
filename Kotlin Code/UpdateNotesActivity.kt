package com.example.assignment_module5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class UpdateNotesActivity : AppCompatActivity()
{
    lateinit var updateNoteDate : EditText
    lateinit var updateNoteHeading : EditText
    lateinit var updateNoteDescription : EditText
    lateinit var UPDATE_btn : Button
    lateinit var dbobject : NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_notes)

        updateNoteDate = findViewById(R.id.UPDATE_getdate)
        updateNoteHeading = findViewById(R.id.UPDATE_getheading)
        updateNoteDescription = findViewById(R.id.UPDATE_getdescription)
        UPDATE_btn = findViewById(R.id.UPDATE_btn)

        dbobject = Room.databaseBuilder(applicationContext,NotesDatabase::class.java,"Notes_Database")
            .allowMainThreadQueries()
            .build()

        var getvalueintent = intent
        var id = getvalueintent.getIntExtra("ID",0)
        updateNoteDate.setText(getvalueintent.getStringExtra("Date"))
        updateNoteHeading.setText(getvalueintent.getStringExtra("Heading"))
        updateNoteDescription.setText(getvalueintent.getStringExtra("Description"))

        UPDATE_btn.setOnClickListener {

            var date = updateNoteDate.text.toString()
            var heading = updateNoteHeading.text.toString()
            var description = updateNoteDescription.text.toString()

            var notesDetails = NotesDetails()
            notesDetails.NoteID = id
            notesDetails.NoteDate = date
            notesDetails.NoteHeading = heading
            notesDetails.NoteDescription = description

            dbobject.NotesDeo().UpdateNotes(notesDetails)

            Toast.makeText(applicationContext,"Note Updated...",Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
    }
}