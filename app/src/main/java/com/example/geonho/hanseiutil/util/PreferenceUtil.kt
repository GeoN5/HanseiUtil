@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.geonho.hanseiutil.util

import android.content.Context
import android.content.SharedPreferences


object SharedPreferenceUtil {

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

    fun saveSwitch(context: Context,key: String,value:Boolean){
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getSwitch(context : Context, key : String) : Boolean {
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key,true)
    }
}