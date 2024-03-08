package com.example.socialnetwork.Interfaces

import com.example.socialnetwork.Models.Post
import retrofit2.Response
import retrofit2.http.GET

interface IPosts {
    @GET("posts")
    suspend fun recuperaPosts(): Response<List<Post>>
}