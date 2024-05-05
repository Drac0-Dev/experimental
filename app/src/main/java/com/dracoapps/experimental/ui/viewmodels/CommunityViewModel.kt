package com.dracoapps.experimental.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracoapps.experimental.ui.data.items.UserItem
import com.dracoapps.experimental.ui.data.network.GetAllUsersRepository
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val repository = GetAllUsersRepository()
    private val _userList = MutableLiveData<List<UserItem>>()
    val userList: LiveData<List<UserItem>> = _userList

    fun getAllUsers(context: Context) {
        viewModelScope.launch {
            repository.getUserList(context) { userList ->
                _userList.postValue(userList)
                Log.d("CommunityViewModel","Los datos obtenidos del respositorio son: $userList")
            }
        }
    }
}