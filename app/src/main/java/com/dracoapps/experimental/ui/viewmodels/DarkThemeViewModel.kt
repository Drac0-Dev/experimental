package com.dracoapps.experimental.ui.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DarkThemeViewModel: ViewModel () {
    private val _isDarkTheme = MutableLiveData<Boolean>()
    fun setIsDarkTheme(response : Boolean){
            _isDarkTheme.value = response
            Log.d("DarkThemeViewModel","El valor de Response del Login Screen es:  $response")
    }

    fun getIsDarkTheme(): Boolean {
            return  _isDarkTheme.value  ?: false
    }


}