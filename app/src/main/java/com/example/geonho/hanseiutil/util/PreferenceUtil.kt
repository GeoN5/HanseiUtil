package com.example.geonho.hanseiutil.util

import android.content.Context


object SharedPreferenceUtil {

    fun saveList(context: Context, key: String , list: MutableList<String>){
        val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet(key, list.toMutableSet()).apply()
    }

    fun getList(context: Context,key: String):List<String>{
        val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(key, mutableSetOf()).toList()
    }

    fun removePreferences(context: Context,key: String){
        val pref = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(key)
        editor.apply()
    }
}