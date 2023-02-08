package com.listview.aktifiti.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Pengguna(
    @PrimaryKey(true)
    val id : Int? = null,
    var nama : String,
    var deskripsi : String
): Parcelable
