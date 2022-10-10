package com.example.githubuser.API

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class tableUsers (
    
    @field:SerializedName("login")
    val userID: String? = null,

    @field:SerializedName("avatar_url")
    val avatar : String? = null,
    val repos_url : String? = null
): Parcelable