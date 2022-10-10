package com.example.githubuser.API

import com.google.gson.annotations.SerializedName

data class DetilUser (
    
    @field:SerializedName("login")
    val username : String? = null,
    
    @field:SerializedName("avatar_url")
    val avatar : String? = null,
    
    @field:SerializedName("followers_url")
    val fwers_url : String? = null,
    @field:SerializedName("following_url")
    val fwing_url : String? = null,
    val name: String? = null,
    val company: String? = null,
    val location: String? = null,
    @SerializedName("public_repos")
    val repos: Int? = null,
    @SerializedName("followers")
    val fwers: Int? = null,
    @SerializedName("following")
    val fwing: Int? = null
    
)
