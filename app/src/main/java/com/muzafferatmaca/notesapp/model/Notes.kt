package com.muzafferatmaca.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Muzaffer Atmaca on 11.06.2022.
 */
@Entity
class Notes: Serializable {

    @ColumnInfo(name = "noteText")
    var noteText: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "subTitle")
    var subTitle: String? = null

    @ColumnInfo(name = "dateTime")
    var dateTime: String? = null

    @ColumnInfo(name = "imgPath")
    var imgPath: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "webLink")
    var webLink: String? = null

    @ColumnInfo(name = "color")
    var color: String? = null

    override fun toString(): String {

        return "$title : $dateTime"
    }

}