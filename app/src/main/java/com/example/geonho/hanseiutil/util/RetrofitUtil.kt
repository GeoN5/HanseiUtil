package com.example.geonho.hanseiutil.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    var retrofit = Retrofit.Builder()
        .baseUrl("http://207.148.88.110:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()!!
}