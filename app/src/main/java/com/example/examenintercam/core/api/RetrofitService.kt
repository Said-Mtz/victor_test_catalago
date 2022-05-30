package com.example.examenintercam.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun instance(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}