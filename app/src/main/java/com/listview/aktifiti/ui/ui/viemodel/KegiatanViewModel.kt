package com.listview.aktifiti.ui.ui.viemodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.repo.Repository

class KegiatanViewModel(application: Application) : ViewModel() {
    private val mRepostory = Repository(application)

    fun insertKegiatan(kegiatan: Kegiatan) = mRepostory.insertKegiatan(kegiatan)

    fun getAllKegiatan(idUser : Int) : LiveData<List<Kegiatan>> = mRepostory.getAllKegiatan(idUser)

    fun deleteKegiatan(kegiatan: Kegiatan) = mRepostory.deleteKegiatan(kegiatan)
}