package com.tunaikumobile.samplemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
@Entity
data class Project(
    @field:PrimaryKey
    val id: Long? = null,
    val name: String? = null,
    val language: String? = null
)