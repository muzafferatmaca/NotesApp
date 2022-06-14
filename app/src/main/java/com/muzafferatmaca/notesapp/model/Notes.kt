package com.muzafferatmaca.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Entity(tableName = "notes")
class Notes(

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

    @ColumnInfo(name = "webLink")
    var webLink: String,

    @ColumnInfo(name = "color")
    var color: String,

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}