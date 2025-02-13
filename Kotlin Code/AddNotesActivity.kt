package com.example.assignment_module5

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import java.util.Calendar

@SuppressLint("StaticFieldLeak")
lateinit var NoteDate : TextView

class AddNotesActivity : AppCompatActivity()
{
    lateinit var NoteHeading : EditText
    lateinit var NoteDescription : EditText
    lateinit var ADD_btn : Button
    lateinit var dbobject : NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        NoteDate = findViewById(R.id.ADD_getdate)
        NoteHeading = findViewById(R.id.ADD_getheading)
        NoteDescription = findViewById(R.id.ADD_getdescription)
        ADD_btn = findViewById(R.id.ADD_btn)

        dbobject = Room.databaseBuilder(applicationContext,NotesDatabase::class.java,"Notes_Database")
            .allowMainThreadQueries()
            .build()

        ADD_btn.setOnClickListener {

            var date = NoteDate.text.toString()
            var heading = NoteHeading.text.toString()
            var description = NoteDescription.text.toString()

            var details = NotesDetails()
            details.NoteDate = date
            details.NoteHeading = heading
            details.NoteDescription = description

            dbobject.NotesDeo().InsertNotes(details)
            Toast.makeText(applicationContext,"Note Added",Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

        NoteDate.setOnClickListener {
            var d1 = NoteDatePicker()
            d1.show(supportFragmentManager,"Select Date")
        }
    }
}


class NoteDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        var c= Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        var year = c.get(Calendar.YEAR)
        var month =  c.get(Calendar.MONTH)

        return DatePickerDialog(requireActivity(),this,year,month+1,day)
    }
    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int)
    {
        NoteDate.setText(""+p3+"-"+p2+"-"+p1)
    }

}