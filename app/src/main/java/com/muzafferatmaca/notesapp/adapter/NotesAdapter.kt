package com.muzafferatmaca.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.ItemNoteBinding
import com.muzafferatmaca.notesapp.model.Notes

/**
 * Created by Muzaffer Atmaca on 13.06.2022.
 */
class NotesAdapter(val noteslist: List<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {



    class ViewHolder(val itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemNoteBinding>(inflater,R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemNoteBinding.notes = noteslist[position]


    }

    override fun getItemCount(): Int {
       return noteslist.size
    }
}