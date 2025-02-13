package com.example.assignment_module5

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesDetails::class], version = 1)
abstract class NotesDatabase : RoomDatabase()
{
    abstract fun NotesDeo() : NoteDao
}