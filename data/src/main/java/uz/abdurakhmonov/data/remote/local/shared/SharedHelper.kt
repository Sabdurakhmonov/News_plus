package uz.abdurakhmonov.data.remote.local.shared

import android.content.SharedPreferences
import javax.inject.Inject

class SharedHelper @Inject constructor(
    private val preferences: SharedPreferences
) {
    companion object{
        const val TIME = "update_time"
        const val MODE = "mode"
        const val API_KEY = "api_key"
    }
    init {
        setKey()
    }
    fun getKey():String{
        return preferences.getString(API_KEY,"").orEmpty()
    }
    private fun setKey(){
        preferences.edit().putString(API_KEY,"ff60ccff16014f379387420563b09bfc").apply()
    }

    fun getModeState():Boolean{
        return preferences.getBoolean(MODE,true)
    }

    fun setModeState():Boolean{
        val state = preferences.getBoolean(MODE,true)
        if(state){
            return preferences.edit().putBoolean(MODE,false).commit()
        }else{
            return preferences.edit().putBoolean(MODE,true).commit()
        }
    }

    fun getTimeUpdate():Long = preferences.getLong(TIME,0L)

    fun setTimeUpdate(time:Long){
        preferences.edit().putLong(TIME,time).apply()
    }
}