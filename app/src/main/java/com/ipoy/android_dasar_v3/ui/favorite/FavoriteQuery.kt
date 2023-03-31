package com.ipoy.android_dasar_v3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteQuery {
    @Insert
    suspend fun add(listFavorite: FavoriteDC)

    @Query("SELECT * FROM list_favorite")
    fun get(): LiveData<List<FavoriteDC>>

    @Query("SELECT COUNT(*) FROM list_favorite WHERE list_favorite.userid = :userid")
    fun check(userid: Int): Int

    @Query("DELETE FROM list_favorite WHERE list_favorite.userid = :userid")
    fun remove(userid: Int): Int
}