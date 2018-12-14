package com.example.geonho.hanseiutil.network

data class Meal(val date:String, val menu:String, val day:String)

data class TimeTable(val name:String)

data class Time(val One:One,val Two:Two)

data class One(
    val mon: List<String>,
    val tue: List<String>,
    val wed: List<String>,
    val thu: List<String>,
    val fri: List<String>
)

data class Two(
    val mon: List<String>,
    val tue: List<String>,
    val wed: List<String>,
    val thu: List<String>,
    val fri: List<String>
)