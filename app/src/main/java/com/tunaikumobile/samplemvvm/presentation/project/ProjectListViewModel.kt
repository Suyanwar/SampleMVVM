package com.tunaikumobile.samplemvvm.presentation.project

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.tunaikumobile.samplemvvm.R
import com.tunaikumobile.samplemvvm.base.BaseViewModel
import com.tunaikumobile.samplemvvm.model.Project
import com.tunaikumobile.samplemvvm.network.GitHubService
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
class ProjectListViewModel: BaseViewModel() {

    companion object {
        const val USER = "Suyanwar"
    }

    @Inject
    lateinit var gitHubService: GitHubService

    private lateinit var subscription: Disposable

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadProjects() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val projectListAdapter: ProjectListAdapter = ProjectListAdapter()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        subscription = gitHubService.getProjectList(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(result: List<Project>){
        projectListAdapter.updatePostList(result)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}