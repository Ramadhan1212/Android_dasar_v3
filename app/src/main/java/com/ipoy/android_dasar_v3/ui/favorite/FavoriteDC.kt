package com.ipoy.android_dasar_v3.ui.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "list_favorite")
data class FavoriteDC(
    @SerializedName("login")
    val username: String,

    @PrimaryKey
    @SerializedName("id")
    val userid: Int,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("html_url")
    val link: String,

    val type: String
): Serializable
