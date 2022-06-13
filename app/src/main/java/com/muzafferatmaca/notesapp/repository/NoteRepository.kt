package com.muzafferatmaca.notesapp.repository

import com.muzafferatmaca.notesapp.database.NoteDatabase
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
class NoteRepository(private val database: NoteDatabase) {

    fun getNote() =
        database.noteDao().allNote

    suspend fun addNote(note : Notes) =
        database.noteDao().insertNote(note)

    suspend fun deleteNote(note: Notes)=
        database.noteDao().deleteNote(note)

}