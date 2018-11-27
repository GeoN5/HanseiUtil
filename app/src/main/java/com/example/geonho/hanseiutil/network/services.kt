package com.example.geonho.hanseiutil.network

import retrofit2.Call
import retrofit2.http.GET



interface MealService{
    @GET("http://api.hansei.us/")
    fun meal(): Call<meal>
}