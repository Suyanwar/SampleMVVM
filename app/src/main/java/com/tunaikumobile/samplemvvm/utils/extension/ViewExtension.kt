package com.tunaikumobile.samplemvvm.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}