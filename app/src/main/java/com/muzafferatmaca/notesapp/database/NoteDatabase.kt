package com.muzafferatmaca.notesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Database(entities = [Notes::class],version = 1)
abstract class NoteDatabase: RoomDatabase() {




}