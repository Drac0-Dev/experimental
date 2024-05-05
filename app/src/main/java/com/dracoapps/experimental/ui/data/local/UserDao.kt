package com.dracoapps.experimental.ui.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    //Si el objeto existe lo actualiza, sino lo inserta
    @Upsert
    suspend fun upsertUser(user:User)
    @Query ("SELECT * FROM users WHERE id = 1")
    suspend fun getLastRegisteredUser(): User?
    @Query("DELETE FROM users ")
    suspend fun deleteAllUsers()
    @Delete
    suspend fun deleteUser(user:User)
    @Query ("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId:String): User?
    @Query ("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByName(username:String): User?
    @Query ("UPDATE users SET userId = :userId WHERE username = :username")
    suspend fun updateUserId (userId:String, username:String)
    @Query ("UPDATE users SET username = :username WHERE userId = :userId")
    suspend fun updateUserName(userId:String, username:String)
    @Query ("UPDATE users SET password = :password WHERE userId = :userId")
    suspend fun updateUserPwd (userId:String, password:String)
    @Query ("UPDATE users SET isAuth = :isAuth WHERE username = :param OR email = :param")
    suspend fun updateAuth (param:String, isAuth:Boolean)
    @Query ("UPDATE users SET urlPic = :urlPic WHERE userId = :userId")
    suspend fun updateUrlPic (userId:String, urlPic:String)
    @Query ("UPDATE users SET description = :description WHERE userId = :userId")
    suspend fun updateDescription (userId:String, description:String)
    @Query("UPDATE sqlite_sequence SET seq=0 WHERE name = 'users' ")
    suspend fun resetIdSeq()
}