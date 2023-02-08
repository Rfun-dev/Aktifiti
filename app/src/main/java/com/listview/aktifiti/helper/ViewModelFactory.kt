package com.listview.aktifiti.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.ui.ui.viemodel.KegiatanViewModel
import com.listview.aktifiti.ui.ui.viemodel.PenggunaViewModel

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE : ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) : ViewModelFactory{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PenggunaViewModel::class.java)) {
            return PenggunaViewModel(mApplication) as T
        }else if(modelClass.isAssignableFrom(KegiatanViewModel::class.java)){
            return KegiatanViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}