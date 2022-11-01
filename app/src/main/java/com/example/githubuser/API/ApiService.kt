package com.example.githubuser.API

import com.example.githubuser.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    
    @Headers("Authorization: token ${BuildConfig.TOKENKEY}")
    @GET("search/users")
    fun getUsers(@Query("q") query: String): Call<DbUsers>
    
    @Headers("Authorization: token ${BuildConfig.TOKENKEY}")
    @GET("users/{userid}")
    fun getDetailUser(@Path("userid") userid: String): Call<DetilUser>
    
    @GET("users/{userid}/{subid}")
    @Headers("Authorization: token ${BuildConfig.TOKENKEY}")
    fun getFollowers(
        @Path("userid") userid: String,
        @Path("subid") subid: String
        ): Call<List<tableUsers>>
    
    @GET("users/{userid}/repos")
    @Headers("Authorization: token ${BuildConfig.TOKENKEY}")
    fun getRepos(@Path("userid") userid: String): Call<List<reposUser>>
    
}
