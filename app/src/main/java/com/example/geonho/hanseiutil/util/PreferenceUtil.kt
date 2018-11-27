package com.example.geonho.hanseiutil.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {

    fun List<String>.saveList(context: Context,key: String){
        val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet(key, this.toMutableSet()).apply()
    }

    fun Context.getList(key: String):List<String>{
        val sharedPreferences = getSharedPreferences("test",Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(key, mutableSetOf()).toList()
    }

}