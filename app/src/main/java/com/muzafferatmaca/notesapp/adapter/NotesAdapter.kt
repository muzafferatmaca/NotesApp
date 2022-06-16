package com.muzafferatmaca.notesapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muzafferatmaca.notesapp.R
import com.muzafferatmaca.notesapp.databinding.ItemNoteBinding
import com.muzafferatmaca.notesapp.model.Notes
import com.muzafferatmaca.notesapp.viewmodel.CreateFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.android.synthetic.main.item_note.view.imgNote

/**
 * Created by Muzaffer Atmaca on 13.06.2022.
 */
class NotesAdapter(var noteslist: List<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {



    class ViewHolder(val itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemNoteBinding>(inflater,R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemNoteBinding.notes = noteslist[position]

        if (noteslist[position].color != null){
            holder.itemNoteBinding.cardView.setCardBackgroundColor(Color.parseColor(noteslist[position].color))
        }else{
            holder.itemNoteBinding.cardView.setCardBackgroundColor(Color.parseColor(R.color.black.toString()))
        }

        if (noteslist[position].imgPath != null){
            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(noteslist[position].imgPath))
            holder.itemView.imgNote.visibility = View.VISIBLE
        }else{
            holder.itemView.imgNote.visibility = View.GONE
        }

        if (noteslist[position].webLink != ""){
            holder.itemView.webLink.text = noteslist[position].webLink
            holder.itemView.webLink.visibility = View.VISIBLE
        }else{
            holder.itemView.webLink.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = noteslist.size
}