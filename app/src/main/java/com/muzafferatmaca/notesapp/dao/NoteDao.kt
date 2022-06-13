package com.muzafferatmaca.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNote : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)


}