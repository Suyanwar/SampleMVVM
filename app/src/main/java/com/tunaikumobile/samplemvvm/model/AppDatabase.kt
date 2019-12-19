package com.tunaikumobile.samplemvvm.model

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 *
 * Created by Suyanwar on 2019-12-19.
 * Android Engineer
 *
 **/
@Database(entities = [Project::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}