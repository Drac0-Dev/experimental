package com.dracoapps.experimental.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracoapps.experimental.ui.data.local.AppDatabase
import com.dracoapps.experimental.ui.data.local.User
import com.dracoapps.experimental.ui.data.network.RegUserRepository
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel () {

    //Lógica para almacenar los datos del usuario en el dispositivo
    private val userDao = AppDatabase.db.userDao()
    fun upsertUser (user: User) {
        viewModelScope.launch {
            userDao.upsertUser(user)
        }
    }

    //Lógica para realizar el registro del usuario
    private val regUserRepository = RegUserRepository()
    private val _regResult = MutableLiveData<Boolean>()
    val regResult : LiveData<Boolean> = _regResult
    fun regNewUser(context: Context, email: String, username: String, password: String) {
        regUserRepository.regNewUser(context, email, username, password) {success->
          _regResult.postValue(success)
        }
    }
}