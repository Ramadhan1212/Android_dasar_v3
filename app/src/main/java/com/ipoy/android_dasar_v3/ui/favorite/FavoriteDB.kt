package com.ipoy.android_dasar_v3.ui.favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteDC::class],
    version = 1
)

abstract class FavoriteDB : RoomDatabase() {
    companion object{
        var inst: FavoriteDB? = null

        fun get(c: Context): FavoriteDB?{
            if (inst == null) {
                synchronized(FavoriteDB::class) {
                    inst = Room.databaseBuilder(c.applicationContext, FavoriteDB::class.java, "list_favorite").build()
                }
            }
            return inst
        }
    }

    abstract fun DAO(): FavoriteQuery

}