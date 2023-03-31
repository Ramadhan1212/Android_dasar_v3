package com.ipoy.android_dasar_v3.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ipoy.android_dasar_v3.connection.Connection
import com.ipoy.android_dasar_v3.data.Responses
import com.ipoy.android_dasar_v3.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val list = MutableLiveData<ArrayList<User>>()

    fun set(query: String){
        Connection.apiIns
            .getSearchUsers(query)
            .enqueue(object : Callback<Responses>{
                override fun onResponse(call: Call<Responses>, response: Response<Responses>) {
                    if (response.isSuccessful){
                        list.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<Responses>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun get(): LiveData<ArrayList<User>>{
        return list
    }
}