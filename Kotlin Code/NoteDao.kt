package com.example.assignment_module5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao
{
    @Insert
    fun InsertNotes(note : NotesDetails)

    @Query("select * from Notes_Details")
    fun ViewNotes() : MutableList<NotesDetails>

    @Update
    fun UpdateNotes(note: NotesDetails)

    @Delete
    fun DeleteNotes(note: NotesDetails)
}