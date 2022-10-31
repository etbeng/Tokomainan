package com.example.githubuser.API

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    
    @Headers("Authorization: token $TOKEN_KEY")
    @GET("search/users")
    fun getUsers(@Query("q") query: String): Call<DbUsers>
    
    @Headers("Authorization: token $TOKEN_KEY")
    @GET("users/{userid}")
    fun getDetailUser(@Path("userid") userid: String): Call<DetilUser>
    
    @GET("users/{userid}/followers")
    @Headers("Authorization: token $TOKEN_KEY")
    fun getFollowers(@Path("userid") userid: String): Call<List<tableUsers>>
    
    @GET("users/{userid}/following")
    @Headers("Authorization: token $TOKEN_KEY")
    fun getFollowing(@Path("userid") userid: String): Call<List<tableUsers>>
    
    @GET("users/{userid}/repos")
    @Headers("Authorization: token $TOKEN_KEY")
    fun getRepos(@Path("userid") userid: String): Call<List<reposUser>>
    
    companion object {
        private const val TOKEN_KEY = "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"
    }
    
}
