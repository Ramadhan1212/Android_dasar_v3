package com.ipoy.android_dasar_v3.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val username: String,

    @SerializedName("id")
    val userid: Int,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("html_url")
    val link: String,

    val type: String
)
