package com.muzafferatmaca.notesapp.view

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.adapter.NotesAdapter
import com.muzafferatmaca.notesapp.databinding.FragmentMainBinding
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    private var notesAdapter = NotesAdapter(arrayListOf())
    var notesList = ArrayList<Notes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        recyclerViewDisplay()
        fabButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCreateFragment()
            Navigation.findNavController(view).navigate(action)
        }


    }

    private fun recyclerViewDisplay() {
        @SuppressLint("SwitchIntDef")
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> setUpRecyclerView(2)
            Configuration.ORIENTATION_LANDSCAPE -> setUpRecyclerView(3)
        }
    }

    private fun setUpRecyclerView(spanCount: Int) {

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = notesAdapter
            observeData()
        }
    }

    private fun observeData() {

        viewModel.getAllNote().observe(viewLifecycleOwner) {
            notesAdapter.noteslist = it
            notesAdapter.notifyDataSetChanged()
        }

    }

}