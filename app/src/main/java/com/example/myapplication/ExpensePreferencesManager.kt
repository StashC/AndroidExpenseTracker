package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object ExpensePreferencesManager {
    lateinit var sp : SharedPreferences

    private const val FILE_NAME = "expenses_preferences"

    //get the store shared preferences
    fun with(application: Application){
        sp = application.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(obj : T, key: String){
        //put the object under the given string as the key
        val jsonString = GsonBuilder().create().toJson(obj)
        //save the JSON string in shared preferences
        sp.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = sp.getString(key, null)
        val retval = GsonBuilder().create().fromJson(value, T::class.java)
        return retval;
    }
}