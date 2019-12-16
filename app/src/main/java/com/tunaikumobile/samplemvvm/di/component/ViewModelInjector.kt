package com.tunaikumobile.samplemvvm.di.component

import com.tunaikumobile.samplemvvm.di.module.NetworkModule
import com.tunaikumobile.samplemvvm.presentation.project.ProjectListViewModel
import dagger.Component
import javax.inject.Singleton


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(projectListViewModel: ProjectListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}