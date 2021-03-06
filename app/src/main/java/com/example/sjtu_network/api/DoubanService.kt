package com.example.sjtu_network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://movie.querydata.org/api?id=25845392

interface DoubanService {
    @GET("api")
    fun getMovieInfo(@Query("id") id: Int): Call<DoubanBean>
}