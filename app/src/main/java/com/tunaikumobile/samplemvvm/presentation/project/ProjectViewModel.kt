package com.tunaikumobile.samplemvvm.presentation.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunaikumobile.samplemvvm.model.Project


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectViewModel : ViewModel() {
    private val projectName = MutableLiveData<String>()
    private val projectLanguage = MutableLiveData<String>()

    fun bind(project: Project) {
        projectName.value = project.name
        projectLanguage.value = project.language
    }

    fun getProjectName(): MutableLiveData<String> {
        return projectName
    }

    fun getProjectLanguage(): MutableLiveData<String> {
        return projectLanguage
    }
}