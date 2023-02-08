package com.listview.aktifiti.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna

@Database(entities = [Pengguna::class, Kegiatan::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getDao() : Dao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context) : AppDatabase {
            if(INSTANCE == null){
                synchronized(AppDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}