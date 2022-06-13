package com.muzafferatmaca.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.muzafferatmaca.notesapp.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
class MainFragmentViewModel(application: Application) : BaseViewModel(application) {



    fun getAllNotesDatabase() = viewModelScope.launch(Dispatchers.IO){
        val notes = NoteDatabase.invoke(getApplication()).noteDao().getAllNote()

    }


}