package com.example.socialnetwork.Interfaces

import com.example.socialnetwork.Models.Nome
import com.example.socialnetwork.Models.Post
import retrofit2.Response
import retrofit2.http.GET

interface INomes {
    @GET("nomes")
    suspend fun recuperaNomes(): Response<List<Nome>>
}