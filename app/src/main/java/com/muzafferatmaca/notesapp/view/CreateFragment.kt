package com.muzafferatmaca.notesapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import com.muzafferatmaca.notesapp.util.NoteBottomSheetFragment
import com.muzafferatmaca.notesapp.viewmodel.CreateFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import java.text.SimpleDateFormat
import java.util.*


class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateFragmentViewModel
    private val currentDate = SimpleDateFormat.getDateInstance().format(Date())
    var selectedColor = "#191A2C"
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
        setDoneImageView()
        setBackImageView()
        setMoreImageView()

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
        }

    }

    private fun setDoneImageView() {

        doneImageView.setOnClickListener {

            val action = CreateFragmentDirections.actionCreateFragmentToMainFragment()
            Navigation.findNavController(it).navigate(action)
            saveNote()
        }

    }

    private fun setBackImageView() {

        backImageView.setOnClickListener {

            val action = CreateFragmentDirections.actionCreateFragmentToMainFragment()
            Navigation.findNavController(it).navigate(action)

        }

    }

    private fun setMoreImageView() {

        var noteBottomSheetFragment = NoteBottomSheetFragment.newInstance()
        noteBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "Note Bottom Sheet Fragment"
        )

    }

    private val BroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var actionColor = intent!!.getStringExtra("action")

            when (actionColor!!) {


                "Blue" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Green" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Black" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Image" ->{
                    readStorageTask()
                    layoutWebUrl.visibility = View.GONE
                }

                "WebUrl" ->{
                    layoutWebUrl.visibility = View.VISIBLE
                }
                "DeleteNote" -> {
                    //delete note
                    deleteNote()
                }


                else -> {
                    layoutImage.visibility = View.GONE
                    imgNote.visibility = View.GONE
                    layoutWebUrl.visibility = View.GONE
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

            }

        }

    }

}