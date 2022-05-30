package com.example.examenintercam.core.api

import com.example.examenintercam.core.model.BeerRequestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiBeerService {
    @GET("v2/beers")
    fun getBeer(@Query("page") pageId: String): Call<BeerRequestModel>
}