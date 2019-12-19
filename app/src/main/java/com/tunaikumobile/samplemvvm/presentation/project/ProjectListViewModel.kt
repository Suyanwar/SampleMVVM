package com.tunaikumobile.samplemvvm.presentation.project

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.tunaikumobile.samplemvvm.R
import com.tunaikumobile.samplemvvm.base.BaseViewModel
import com.tunaikumobile.samplemvvm.model.Project
import com.tunaikumobile.samplemvvm.model.ProjectDao
import com.tunaikumobile.samplemvvm.network.GitHubService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectListViewModel(private val projectDao: ProjectDao) : BaseViewModel() {

    companion object {
        const val USER = "Suyanwar"
    }

    @Inject
    lateinit var gitHubService: GitHubService

    private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadProjects() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val projectListAdapter: ProjectListAdapter = ProjectListAdapter()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        subscription = Observable.fromCallable { projectDao.all }
            .concatMap { dbPostList ->
                if (dbPostList.isEmpty()) gitHubService.getProjectList(USER).concatMap { apiProjectList ->
                    projectDao.insertAll(*apiProjectList.toTypedArray())
                    Observable.just(apiProjectList)
                }
                else
                    Observable.just(dbPostList)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveProjectListStart() }
            .doOnTerminate { onRetrieveProjectListFinish() }
            .subscribe(
                { result -> onRetrieveProjectListSuccess(result) },
                { onRetrieveProjectListError() }
            )
    }

    private fun onRetrieveProjectListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveProjectListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveProjectListSuccess(result: List<Project>) {
        projectListAdapter.updateProjectList(result)
    }

    private fun onRetrieveProjectListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}