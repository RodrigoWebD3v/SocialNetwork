package com.example.socialnetwork.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {

        val jsonPlaceHolder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ibgNomes = Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/api/v2/censos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}