package com.dracoapps.experimental.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (
    entities = [User::class],
    version = 2,
    exportSchema = false)

abstract class DataBase :RoomDatabase () {
    abstract fun userDao(): UserDao
}