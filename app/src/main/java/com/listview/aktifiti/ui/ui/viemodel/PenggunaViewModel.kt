package com.listview.aktifiti.ui.ui.viemodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.repo.Repository

class PenggunaViewModel(application: Application) : ViewModel(){
    private val mRepostory = Repository(application)

    fun insertPengguna(pengguna: Pengguna) = mRepostory.insertPengguna(pengguna)

    fun getAllPengguna() : LiveData<List<Pengguna>> = mRepostory.getAllPengguna()

    fun deletePengguna(pengguna: Pengguna) = mRepostory.deletePengguna(pengguna)

    fun updatePengguna(pengguna: Pengguna) = mRepostory.updatePengguna(pengguna)
}