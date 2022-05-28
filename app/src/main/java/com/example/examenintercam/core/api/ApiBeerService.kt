package com.example.examenintercam.core.api

import com.example.examenintercam.core.model.BeerRequestModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiBeerService {
    @GET("beers")
    fun getBeer(): Call<BeerRequestModel>
}