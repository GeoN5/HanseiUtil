package com.example.geonho.hanseiutil.util

import android.content.Context
import android.content.SharedPreferences


object SharedPreferenceUtil {

    fun saveList(context: Context, key: String , list: MutableList<String>){
        val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        //set은 index 장담을 못함 이거땜에 망했다
        editor.putStringSet(key, list.toMutableSet()).apply()
    }

    fun getList(context: Context,key: String):List<String>{
        val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(key, mutableSetOf()).toList()
    }

    fun getData(context : Context, key : String) : String {
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "default")
    }

    fun saveData(context: Context, key : String, value : String) {
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun removePreferences(context: Context,key: String){
        val pref = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(key)
        editor.apply()
    }
}