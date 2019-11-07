package com.abhishek.mvvmdemo

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by abhishek
 * on 18/03/15.
 */
open class LocalStorage(private val prefName: String, val context: Context,
                        private val preferences: SharedPreferences) {

    fun storeData(key: String, value: String?) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue)!!
    }

    fun getFloat(key: String): Float {
        return preferences.getFloat(key, 0.0f)
    }

    fun getLong(key: String): Long {
        return preferences.getLong(key, 0)
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}
