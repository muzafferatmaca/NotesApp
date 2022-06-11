package com.muzafferatmaca.notesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.FragmentCreateBinding
import kotlinx.android.synthetic.main.fragment_create.*
import java.text.SimpleDateFormat
import java.util.*


class CreateFragment : Fragment() {

    private lateinit var binding : FragmentCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val simpleDateFormat = SimpleDateFormat()
        val currentDate = simpleDateFormat.format(Date())

        dateTimeTextView.text = currentDate

        doneImageView.setOnClickListener {
             saveNote()
        }
    }

    private fun saveNote(){

        if (addNoteTitleEditText.text.isNullOrEmpty()){

        }

    }
}