package com.dracoapps.experimental.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracoapps.experimental.ui.data.items.AuthResponse
import com.dracoapps.experimental.ui.data.items.UserItem
import com.dracoapps.experimental.ui.data.local.AppDatabase
import com.dracoapps.experimental.ui.data.local.User
import com.dracoapps.experimental.ui.data.network.AuthUserRepository
import com.dracoapps.experimental.ui.data.network.GetUserByIdRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel () {
    //Lógica para autenticar al usuario
    private val authUserRepository = AuthUserRepository()
    private val _authResult = MutableLiveData<AuthResponse>()
    val authResult : LiveData<AuthResponse> = _authResult
    fun authUser(context : Context, param: String, pwd: String){
        authUserRepository.authUser(context, param, pwd){success->
            _authResult.postValue(success)
        }
    }
    //Obtiene los datos del usuario del servidor
    private val getUserByIdRepository = GetUserByIdRepository()
    private val _user = MutableLiveData<UserItem?>()
    val user : LiveData<UserItem?> = _user
    fun getUserById (context: Context, userid: String) {
       getUserByIdRepository.getUserById(context, userid){ response->
           _user.postValue(response)
           Log.d("LoginViewModel","Los datos del usuario fueron: $response")
       }
    }

    //Lógica para almacenar los datos del usuario en el dispositivo
    private val userDao = AppDatabase.db.userDao()
    fun upsertUser (user: User) {
        viewModelScope.launch {
            userDao.upsertUser(user)
        }
    }
    //Lógica para borrar todos los registros existentes
    fun deleteAllUsers () {
        viewModelScope.launch {
            userDao.deleteAllUsers()
        }
    }

    //Reset AutoIncrement
    fun resetAutoInc() {
        viewModelScope.launch {
            userDao.resetIdSeq()
        }
    }

    private val _userRoom = MutableLiveData<User>()
    val userRoom : LiveData<User> = _userRoom
    fun getUserRoom (){
        viewModelScope.launch {
            _userRoom.value = userDao.getLastRegisteredUser()
        }
    }
}