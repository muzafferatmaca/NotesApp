package com.muzafferatmaca.notesapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.FragmentCreateBinding
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.util.NoteBottomSheetFragment
import com.muzafferatmaca.notesapp.viewmodel.CreateFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*


class CreateFragment : Fragment(), EasyPermissions.PermissionCallbacks,EasyPermissions.RationaleCallbacks {

    private lateinit var viewModel: CreateFragmentViewModel
    private val currentDate = SimpleDateFormat.getDateInstance().format(Date())
    var selectedColor = "#191A2C"
    private var webLink = ""
    private var selectedImagePath = ""
    private var noteId = -1
    private var READ_STORAGE_PERM = 100
    private var REQUEST_CODE_IMAGE = 200


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

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadCastReceiver, IntentFilter("bottom_sheet_action")
        )

        dateTimeTextView.text = currentDate
        colorView.setBackgroundColor(Color.parseColor(selectedColor))
        setDoneImageView()
        setBackImageView()
       // setClickMoreImageView()
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

            requireActivity().supportFragmentManager.popBackStack()
           /* val action = CreateFragmentDirections.actionCreateFragmentToMainFragment()
            Navigation.findNavController(it).navigate(action)

            */

        }

    }

    private fun setClickMoreImageView(){
        noteMore.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setMoreImageView() {

        noteMore.setOnClickListener {
            var noteBottomSheetFragment = NoteBottomSheetFragment.newInstance(noteId)
            noteBottomSheetFragment.show(requireActivity().supportFragmentManager, "Note Bottom Sheet Fragment"
            )
        }

    }

    private val broadCastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var actionColor = intent!!.getStringExtra("action")

            when (actionColor!!) {


                "Blue" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Yellow" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Purple" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Green" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Orange" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Black" -> {
                    selectedColor = intent.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

            }

        }

    }

    override fun onDestroy() {

      LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadCastReceiver)
        super.onDestroy()

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }

}