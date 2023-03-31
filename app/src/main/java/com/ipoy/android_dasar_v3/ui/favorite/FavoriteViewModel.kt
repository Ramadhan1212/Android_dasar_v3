package com.ipoy.android_dasar_v3.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: FavoriteQuery?
    private var db: FavoriteDB? = FavoriteDB.get(application)

    init {
        dao = db?.DAO()
    }

    fun get() : LiveData<List<FavoriteDC>>?{
        return dao?.get()
    }
}