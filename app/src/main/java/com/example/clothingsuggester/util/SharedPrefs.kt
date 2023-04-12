package com.example.clothingsuggester.util

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    private lateinit var sharedPreferences: SharedPreferences

    fun initSharedPref(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    var clothId: Int?
        get() = sharedPreferences.getInt(Constants.KEY_CLOTH, 0)
        set(value) = sharedPreferences.edit().putInt(Constants.KEY_CLOTH, value!!).apply()

}