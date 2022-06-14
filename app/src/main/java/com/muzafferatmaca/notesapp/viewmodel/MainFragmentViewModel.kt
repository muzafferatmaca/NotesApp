package com.muzafferatmaca.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muzafferatmaca.notesapp.database.NoteDatabase
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val noteDatabase = NoteDatabase(getApplication())
    private val noteRepository = NoteRepository(noteDatabase)

    fun getAllNote(): LiveData<List<Notes>> = noteRepository.getNote()



}