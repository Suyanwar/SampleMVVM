package com.tunaikumobile.samplemvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


/**
 *
 * Created by Suyanwar on 2019-12-19.
 * Android Engineer
 *
 **/
@Dao
interface ProjectDao {
    @get:Query("SELECT * FROM project")
    val all: List<Project>

    @Insert
    fun insertAll(vararg projects: Project)
}