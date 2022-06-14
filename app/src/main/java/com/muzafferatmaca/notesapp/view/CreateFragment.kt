package com.muzafferatmaca.notesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.FragmentCreateBinding
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.viewmodel.CreateFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import java.text.SimpleDateFormat
import java.util.*


class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateFragmentViewModel
    private var notes: Notes? = null
    private val currentDate = SimpleDateFormat.getDateInstance().format(Date())
    var selectedColor = "#171C26"
    private var webLink = ""
    private var selectedImagePath = ""


    private lateinit var binding: FragmentCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateFragmentViewModel::class.java)


        dateTimeTextView.text = currentDate

        doneImageView.setOnClickListener {

            val action = CreateFragmentDirections.actionCreateFragmentToMainFragment()
            Navigation.findNavController(view).navigate(action)
            saveNote()
        }
    }

    private fun saveNote() {

        if (addNoteTitleEditText.text.isNullOrEmpty()) {

            Toast.makeText(context, "test", Toast.LENGTH_LONG).show()

        } else if (addNoteSubTitleEditText.text.isNullOrEmpty()) {

            Toast.makeText(context, "test", Toast.LENGTH_LONG).show()

        } else if (addNoteDescEditText.text.isNullOrEmpty()) {

            Toast.makeText(context, "test", Toast.LENGTH_LONG).show()

        } else {

                viewModel.saveNote(
                    Notes(
                        binding.addNoteDescEditText.text.toString(),
                        binding.addNoteTitleEditText.text.toString(),
                        binding.addNoteSubTitleEditText.text.toString(),
                        currentDate,
                        selectedImagePath,
                        webLink,
                        selectedColor
                    )
                )



            /*
              val descTitle =binding.addNoteDescEditText.text.toString()
                val noteTitle =binding.addNoteTitleEditText.text.toString()
                val subTitle =binding.addNoteSubTitleEditText.text.toString()
                notes = Notes(descTitle,noteTitle ,subTitle,currentDate,selectedImagePath,0,webLink,selectedColor)

                viewModel.saveNote(notes)
             */


        }

    }

}