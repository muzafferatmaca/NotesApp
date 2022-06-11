package com.muzafferatmaca.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Entity(tableName = "Notes")
data class Notes(

    @ColumnInfo(name = "noteText")
    var noteText: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "subTitle")
    var subTitle: String,
    @ColumnInfo(name = "dateTime")
    var dateTime: String,
    @ColumnInfo(name = "imgPath")
    var imgPath: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "webLink")
    var webLink: String,
    @ColumnInfo(name = "color")
    var color: String
){
    override fun toString(): String {

        return "$title : $dateTime"
    }
}