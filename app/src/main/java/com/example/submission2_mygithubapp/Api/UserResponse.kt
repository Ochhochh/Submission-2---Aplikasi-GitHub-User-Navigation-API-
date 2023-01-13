package com.example.submission2_mygithubapp.Api

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("items")
    val items: ArrayList<User>
)

data class User(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("following")
    val following: String,

    @field:SerializedName("followers")
    val followers: String,

    @field:SerializedName("public_repos")
    val publicRepos: String

)