package uz.assh_abdurakhmonov.newsplus.remote.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedHelper @Inject constructor(
    private val preferences: SharedPreferences
) {
    init {
        setKey()
    }
    fun getKey():String{
        return preferences.getString("Key","").toString()
    }
    private fun setKey(){
        preferences.edit().putString("Key","ff60ccff16014f379387420563b09bfc").apply()
    }
}