package com.muzafferatmaca.notesapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muzafferatmaca.notesapp.database.NoteDatabase
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.repository.NoteRepository
import com.muzafferatmaca.notesapp.view.CreateFragment
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
class CreateFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val noteDatabase = NoteDatabase(getApplication())
    private val noteRepository = NoteRepository(noteDatabase)

    fun saveNote(newNotes: Notes) = viewModelScope.launch(Dispatchers.Main) {
        noteRepository.addNote(newNotes)
    }

    fun deleteNote(existingNotes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteNote(existingNotes)
    }

    fun getAllNote(): LiveData<List<Notes>> = noteRepository.getNote()

    fun updateNote(existingNotes: Notes) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.updateNote(existingNotes)
    }

}