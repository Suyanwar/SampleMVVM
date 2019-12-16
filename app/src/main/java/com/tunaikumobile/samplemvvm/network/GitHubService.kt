package com.tunaikumobile.samplemvvm.network

import com.tunaikumobile.samplemvvm.model.Project
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
interface GitHubService {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users/{user}/repos")
    fun getProjectList(@Path("user") user: String?): Observable<List<Project>>
}