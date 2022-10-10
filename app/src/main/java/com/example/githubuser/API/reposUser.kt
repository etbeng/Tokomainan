package com.example.githubuser.API

import com.google.gson.annotations.SerializedName

data class reposUser(
    @field:SerializedName("html_url")
    val htmlUrl: String? = null,
    val description: String? = null
)