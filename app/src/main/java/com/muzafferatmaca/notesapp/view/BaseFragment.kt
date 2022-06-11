package com.muzafferatmaca.notesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.FragmentBaseBinding


class BaseFragment : Fragment() {

    private lateinit var binding : FragmentBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base,container,false)
        return binding.root
    }


}