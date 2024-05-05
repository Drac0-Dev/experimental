package com.dracoapps.experimental.ui.data.local

import android.app.Application
import androidx.room.Room

class AppDatabase : Application() {
    companion object {
        lateinit var db: DataBase private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, DataBase::class.java,
            "localDatabase")
            //.addMigrations(Migration1to2())
            .build()
    }
}

