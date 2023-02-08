package com.listview.aktifiti.db
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna

@Dao
interface Dao {
//    Pengguna
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPengguna(Pengguna : Pengguna)

    @Query("SELECT * FROM pengguna")
    fun getAllPengguna() : LiveData<List<Pengguna>>

    @Delete
    fun deletePengguna(pengguna : Pengguna)

    @Update
    fun updatePengguna(pengguna: Pengguna)

//    Kegiatan

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertKegiatan(Pengguna : Kegiatan)

    @Query("SELECT * FROM kegiatan WHERE idUser IN (:idUser)")
    fun getAllKegiatan(idUser : Int) : LiveData<List<Kegiatan>>

    @Delete
    fun deleteKegiatan(pengguna : Kegiatan)

}