package com.example.assignment_module5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Details")
class NotesDetails
{
    @PrimaryKey(autoGenerate = true)
    var NoteID : Int = 0

    @ColumnInfo(name = "NotesDate")
    var NoteDate : String = ""

    @ColumnInfo(name = "NotesHeading")
    var NoteHeading : String = ""

    @ColumnInfo(name = "NotesDescription")
    var NoteDescription : String = ""
}