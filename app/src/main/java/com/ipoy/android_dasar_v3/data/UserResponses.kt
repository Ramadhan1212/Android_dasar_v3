package com.ipoy.android_dasar_v3.data

import com.google.gson.annotations.SerializedName

data class UserResponses(
    @SerializedName("login")
    val username: String,

    @SerializedName("id")
    val userid: Int,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("followers_url")
    val followers_page: String,

    @SerializedName("following_url")
    val following_page: String,

    val name: String,

    @SerializedName("company")
    val workplace: String?,

    @SerializedName("location")
    val home: String?,

    @SerializedName("public_repos")
    val repository: String,

    @SerializedName("followers")
    val followers_count: String,

    @SerializedName("following")
    val following_count: String
)
