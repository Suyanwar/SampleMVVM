package com.tunaikumobile.samplemvvm.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.tunaikumobile.samplemvvm.model.AppDatabase
import com.tunaikumobile.samplemvvm.presentation.project.ProjectListViewModel


/**
 *
 * Created by Suyanwar on 2019-12-19.
 * Android Engineer
 *
 **/
class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "projects").build()
            @Suppress("UNCHECKED_CAST")
            return ProjectListViewModel(db.projectDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}