package com.listview.aktifiti.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.listview.aktifiti.db.AppDatabase
import com.listview.aktifiti.db.Dao
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(application: Application) {
    private val mDao : Dao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.getInstance(application)
        mDao = db.getDao()
    }

    fun getAllPengguna() : LiveData<List<Pengguna>> = mDao.getAllPengguna()

    fun insertPengguna(pengguna: Pengguna) = executorService.execute {
        mDao.insertPengguna(pengguna)
    }


    fun deletePengguna(pengguna : Pengguna) = executorService.execute {
        mDao.deletePengguna(pengguna)
    }

    fun updatePengguna(pengguna: Pengguna) = executorService.execute {
        mDao.updatePengguna(pengguna)
    }

    fun getAllKegiatan(idUser : Int) : LiveData<List<Kegiatan>> = mDao.getAllKegiatan(idUser)

    fun insertKegiatan(kegiatan: Kegiatan) = executorService.execute{
        mDao.insertKegiatan(kegiatan)
    }

    fun deleteKegiatan(kegiatan: Kegiatan) = executorService.execute{
        mDao.deleteKegiatan(kegiatan)
    }

}