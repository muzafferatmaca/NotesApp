package com.muzafferatmaca.notesapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.muzafferatmaca.notesapp.database.NoteDatabase
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.view.CreateFragment
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.coroutines.launch

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
class CreateFragmentViewModel (application: Application): BaseViewModel(application) {

    private lateinit var  notes : Notes
    private lateinit var createFragment : CreateFragment
    @SuppressLint("StaticFieldLeak")
    val context : Context? = null

     fun storeInSQLite(){

         createFragment = CreateFragment()

        launch {

            notes = Notes()
            notes.title = createFragment.addNoteTitleEditText.text.toString()
            notes.subTitle = createFragment.addNoteSubTitleEditText.text.toString()
            notes.noteText = createFragment.addNoteDescEditText.text.toString()
            notes.dateTime = createFragment.currentDate
            NoteDatabase.invoke(context!!).noteDao().insertNote(notes)
            createFragment.addNoteSubTitleEditText.setText("")
            createFragment.addNoteTitleEditText.setText("")
            createFragment.addNoteDescEditText.setText("")



        }

    }


}