package com.ipoy.android_dasar_v3.ui.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ipoy.android_dasar_v3.connection.Connection
import com.ipoy.android_dasar_v3.data.UserResponses
import com.ipoy.android_dasar_v3.ui.favorite.FavoriteDB
import com.ipoy.android_dasar_v3.ui.favorite.FavoriteDC
import com.ipoy.android_dasar_v3.ui.favorite.FavoriteQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(app: Application) : AndroidViewModel(app) {
    val user = MutableLiveData<UserResponses>()
    private var dao: FavoriteQuery?
    private var db: FavoriteDB?

    init {
        db = FavoriteDB.get(app)
        dao = db?.DAO()
    }

    fun set(un: String) {
        Connection.apiIns
            .getUserDetail(un)
            .enqueue(object : Callback<UserResponses> {
                override fun onResponse(
                    call: Call<UserResponses>,
                    response: Response<UserResponses>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserResponses>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun get(): LiveData<UserResponses> {
        return user
    }

    fun add(un: String, uid: Int, avatar: String, link: String, type: String){
        CoroutineScope(Dispatchers.IO).launch {
            var listUser = FavoriteDC(
                un,
                uid,
                avatar,
                link,
                type
            )
            dao?.add(listUser)
        }
    }

    suspend fun check(userid: Int) = dao?.check(userid)

    fun remove(userid: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao?.remove(userid)
        }
    }
}