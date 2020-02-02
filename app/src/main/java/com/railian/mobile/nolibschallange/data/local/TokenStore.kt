package com.railian.mobile.nolibschallange.data.local

import android.content.SharedPreferences

class TokenStore(private val preferences: SharedPreferences) {
    var token: String by PreferencesDelegate(preferences, TOKEN, "")

    companion object {
        private const val TOKEN = "token"
    }
}