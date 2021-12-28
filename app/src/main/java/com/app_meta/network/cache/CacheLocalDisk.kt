package com.app_meta.network.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object CacheLocalDisk {

    private lateinit var database: SharedPreferences

    fun initDatabase(context: Context) {
        database = context.getSharedPreferences("githubDatabase", Context.MODE_PRIVATE)
    }

    fun exist(tag: String) = database.contains(tag)

    fun <T> find(tag: String, type: Class<T>): T {
        val result = database.getString(tag, "[]")
        return Gson().fromJson(result, type)
    }

    //manipula os dados aqui
    fun <T>save(tag: String, data: T) {
        database
            .edit()
            .putString(tag, Gson().toJson(data))
            .apply()
    }

    fun delete(tag: String) {
        database
            .edit()
            .remove(tag)
            .apply()
    }
}
