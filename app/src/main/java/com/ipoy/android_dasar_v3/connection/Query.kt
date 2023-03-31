package com.ipoy.android_dasar_v3.connection

import com.ipoy.android_dasar_v3.data.Responses
import com.ipoy.android_dasar_v3.data.User
import com.ipoy.android_dasar_v3.data.UserResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Query {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<Responses>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserResponses>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}