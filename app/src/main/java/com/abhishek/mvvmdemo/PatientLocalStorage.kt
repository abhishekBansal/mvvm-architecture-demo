package com.abhishek.mvvmdemo

import android.content.Context
import android.content.SharedPreferences

@OpenForTesting
class PatientLocalStorage(val name: String, ctx: Context, prefs: SharedPreferences) : LocalStorage(name, ctx, prefs) {
    fun setAccessToken(accessToken: String?) {
        storeData(PREF_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken(): String? {
        return getString(PREF_ACCESS_TOKEN)
    }

    fun setRefreshToken(refreshToken: String?) {
        storeData(PREF_REFRESH_TOKEN, refreshToken)
    }

    fun getRefreshToken(): String? {
        return getString(PREF_REFRESH_TOKEN)
    }

    fun setUserId(username: String?) {
        storeData(PREF_USER_ID, username)
    }

    fun getUserId(): String? {
        return getString(PREF_USER_ID)
    }

    fun setUserName(email: String) {
        storeData(PREF_USER_NAME_KEY, email)
    }

    fun getUserName(): String? {
        return getString(PREF_USER_NAME_KEY)
    }

    fun setLastSeenMessageTime(created: Long) {
        storeData(PREF_LAST_SEEN_MESSAGE_TIME, created)
    }

    fun getLastSeenMessageTime(): Long {
        return getLong(PREF_LAST_SEEN_MESSAGE_TIME)
    }

    fun setFireBaseToken(token: String) {
        storeData(PREF_FIREBASE_TOKEN, token)
    }

    fun getFireBaseToken(): String? {
        return getString(PREF_FIREBASE_TOKEN)
    }

    fun setFirebaseTokenRegistered(isRegistered: Boolean) {
        storeData(PREF_IS_FIREBASE_TOKEN_REGISTERED, isRegistered)
    }

    fun isFirebaseTokenRegistered(): Boolean {
        return getBoolean(PREF_IS_FIREBASE_TOKEN_REGISTERED, false)
    }

    fun setAuthId(auth: String) {
        storeData(PREF_AUTH_ID, auth)
    }

    fun getAuthId(): String? {
        return getString(PREF_AUTH_ID)
    }

    companion object {
        const val PREF_ACCESS_TOKEN: String = "pref_access_token"
        const val PREF_REFRESH_TOKEN: String = "pref_refresh_token"
        const val PREF_USER_ID: String = "pref_user_id"
        const val PREF_AUTH_ID: String = "pref_auth_id"
        const val PREF_USER_NAME_KEY: String = "pref_user_name_key"
        const val PREF_LAST_SEEN_MESSAGE_TIME: String = "pref_last_seen_message_time"
        const val PREF_FIREBASE_TOKEN: String = "pref_firebase_token"
        const val PREF_IS_FIREBASE_TOKEN_REGISTERED: String = "pref_is_firebase_token_registered"
    }
}