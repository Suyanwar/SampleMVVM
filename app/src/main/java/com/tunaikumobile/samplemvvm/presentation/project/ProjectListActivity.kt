package com.tunaikumobile.samplemvvm.presentation.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunaikumobile.samplemvvm.R
import com.tunaikumobile.samplemvvm.databinding.ActivityProjectListBinding


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProjectListBinding
    private lateinit var viewModel: ProjectListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_list)
        binding.projectList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        binding.viewModel = viewModel
    }
}