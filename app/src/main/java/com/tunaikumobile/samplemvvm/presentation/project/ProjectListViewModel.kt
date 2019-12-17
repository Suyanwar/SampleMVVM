package com.tunaikumobile.samplemvvm.presentation.project

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunaikumobile.samplemvvm.R
import com.tunaikumobile.samplemvvm.model.Project
import com.tunaikumobile.samplemvvm.network.GitHubService
import com.tunaikumobile.samplemvvm.utils.BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectListViewModel: ViewModel() {

    companion object {
        const val USER = "Suyanwar"
    }

    private var gitHubService: GitHubService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build().create(GitHubService::class.java)

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