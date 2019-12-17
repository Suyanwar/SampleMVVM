package com.tunaikumobile.samplemvvm.presentation.project

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunaikumobile.samplemvvm.model.Project


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectListViewModel: ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val projectListAdapter: ProjectListAdapter = ProjectListAdapter()

    private var result = mutableListOf<Project>()

    init {
        for (i in 1..3) {
            val project = Project(i.toLong(), "Repo-$i", "Language-$i")
            result.add(project)
        }
        loadProjects()
    }

    private fun loadProjects() {
        loadingVisibility.value = View.GONE
        projectListAdapter.updatePostList(result)
    }
}