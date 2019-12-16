package com.tunaikumobile.samplemvvm.base

import androidx.lifecycle.ViewModel
import com.tunaikumobile.samplemvvm.di.component.DaggerViewModelInjector
import com.tunaikumobile.samplemvvm.di.component.ViewModelInjector
import com.tunaikumobile.samplemvvm.di.module.NetworkModule
import com.tunaikumobile.samplemvvm.presentation.project.ProjectListViewModel


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is ProjectListViewModel -> injector.inject(this)
        }
    }
}