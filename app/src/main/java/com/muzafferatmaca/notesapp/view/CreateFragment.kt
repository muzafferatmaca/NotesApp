package com.muzafferatmaca.notesapp.view

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
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
import kotlinx.android.synthetic.main.fragment_create.imgNote
import kotlinx.android.synthetic.main.fragment_create.noteMore
import kotlinx.android.synthetic.main.fragment_note_bottom.*
import kotlinx.android.synthetic.main.item_note.*
import pub.devrel.easypermissions.AppSettingsDialog
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
        buttonSetClick()
        setTvWebLink()

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

    private fun buttonSetClick(){

        btnOk.setOnClickListener {
            if (etWebLink.text.toString().trim().isNotEmpty()){
                checkWebUrl()
            }else{
                Toast.makeText(requireContext(),"Url is Required",Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {

            if (noteId != -1){
                tvWebLink.visibility = View.VISIBLE
                lineWebUrl.visibility = View.GONE
            }else{
                lineWebUrl.visibility = View.GONE
            }
        }
    }

    private fun setTvWebLink(){

        tvWebLink.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW,Uri.parse(etWebLink.text.toString()))
            startActivity(intent)
        }


    }
    private fun checkWebUrl(){
        if (Patterns.WEB_URL.matcher(etWebLink.text.toString()).matches()){
            layoutWebUrl.visibility = View.GONE
            etWebLink.isEnabled = false
            webLink = etWebLink.text.toString()
            tvWebLink.visibility = View.VISIBLE
            tvWebLink.text = etWebLink.text.toString()
        }else{
            Toast.makeText(requireContext(),"Url is not valid",Toast.LENGTH_SHORT).show()
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

                "Image" ->{
                    readStorageTask()
                    layoutWebUrl.visibility = View.GONE
                }

                "WebUrl" ->{
                    lineWebUrl.visibility = View.VISIBLE
                }
                "DeleteNote" -> {
                    //delete note
                }

                else -> {
                    layoutImag.visibility = View.GONE
                    imgNote.visibility = View.GONE
                    layoutWebUrl.visibility = View.GONE
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

    private fun hasReadStoragePerm():Boolean{
        return EasyPermissions.hasPermissions(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
    }


    private fun readStorageTask(){
        if (hasReadStoragePerm()){


            pickImageFromGallery()
        }else{
            EasyPermissions.requestPermissions(requireActivity(), getString(R.string.storage_permission_text),
                READ_STORAGE_PERM, Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun pickImageFromGallery(){
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(requireActivity().packageManager) != null){
            startActivityForResult(intent,REQUEST_CODE_IMAGE)
        }
    }


    private fun getPathFromUri(contentUri: Uri): String? {
        var filePath:String? = null
        var cursor = requireActivity().contentResolver.query(contentUri,null,null,null,null)
        if (cursor == null){
            filePath = contentUri.path
        }else{
            cursor.moveToFirst()
            var index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK){
            if (data != null){
                var selectedImageUrl = data.data
                if (selectedImageUrl != null){
                    try {
                        var inputStream = requireActivity().contentResolver.openInputStream(selectedImageUrl)
                        var bitmap = BitmapFactory.decodeStream(inputStream)
                        imgNote.setImageBitmap(bitmap)
                        imgNote.visibility = View.VISIBLE
                        layoutImag.visibility = View.VISIBLE

                        selectedImagePath = getPathFromUri(selectedImageUrl)!!
                    }catch (e:Exception){
                        Toast.makeText(requireContext(),e.message,Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,requireActivity())
    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(requireActivity(),perms)){
            AppSettingsDialog.Builder(requireActivity()).build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

    override fun onRationaleDenied(requestCode: Int) {
    }

}