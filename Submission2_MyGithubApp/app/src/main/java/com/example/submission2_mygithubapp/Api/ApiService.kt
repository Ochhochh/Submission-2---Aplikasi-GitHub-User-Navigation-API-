package com.example.submission2_mygithubapp.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser (
        @Query ("q") query: String
    ) : Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser (
        @Path ("username") username: String
    ) : Call<User>

    @GET("users/{username}/followers")
    fun getFollowers (
        @Path ("username") username: String
    ) : Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getFollowing (
        @Path ("username") username: String
    ) : Call<ArrayList<User>>
}