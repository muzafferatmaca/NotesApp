package com.muzafferatmaca.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muzafferatmaca.notesapp.dao.NoteDao
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {

        @Volatile
        private var noteDatabaseInstance : NoteDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = noteDatabaseInstance ?: synchronized(lock){

            noteDatabaseInstance ?: makeDatabase(context).also {
                noteDatabaseInstance = it
            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,NoteDatabase::class.java,"note_database").build()


    }




}