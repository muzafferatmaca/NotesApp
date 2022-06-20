package com.muzafferatmaca.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNote(): LiveData<List<Notes>>

    fun updateNote(note: Notes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)


}