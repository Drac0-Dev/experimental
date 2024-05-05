package com.dracoapps.experimental.ui.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracoapps.experimental.ui.data.local.AppDatabase
import com.dracoapps.experimental.ui.data.local.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel () {

    private val userDao = AppDatabase.db.userDao()
    private val _userLast = MutableLiveData<User>()
    val userLast: LiveData<User> = _userLast
    fun getLastUser (){
        viewModelScope.launch {
            _userLast.value = userDao.getLastRegisteredUser()
        }
    }

}