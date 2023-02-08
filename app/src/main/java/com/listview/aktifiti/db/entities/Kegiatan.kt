package com.listview.aktifiti.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Kegiatan(
    @PrimaryKey(true)
    val id : Int? = null,
    val idUser : Int? = null,
    val nama : String,
    val kegiatan : String,
    val waktu : String
) : Parcelable
