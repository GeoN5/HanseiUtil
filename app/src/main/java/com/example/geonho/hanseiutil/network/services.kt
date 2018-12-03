package com.example.geonho.hanseiutil.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface MealService{
    @GET("http://api.hansei.us/")
    fun meal(): Call<meal>
}

interface FirebaseService{
    @FormUrlEncoded
    @POST("http://207.148.88.110:3000/")
    fun getItem(@Field("name") name:String):Call<TimeTable>
}