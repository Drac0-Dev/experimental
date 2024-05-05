package com.dracoapps.experimental.ui.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val userId: String?,
    val email : String?,
    val username: String?,
    val password: String?,
    val isAuth: Boolean?,
    val urlPic: String?,
    val description: String?
)
