package com.example.submission2_mygithubapp.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers ("Authorization: token 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")
    fun getSearchUser (
        @Query ("q") query: String
    ) : Call<UserResponse>

    @GET("users/{username}")
    @Headers ("Authorization: token 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")
    fun getDetailUser (
        @Path ("username") username: String
    ) : Call<User>

    @GET("users/{username}/followers")
    @Headers ("Authorization: token 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")
    fun getFollowers (
        @Path ("username") username: String
    ) : Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers ("Authorization: token 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")
    fun getFollowing (
        @Path ("username") username: String
    ) : Call<ArrayList<User>>
}