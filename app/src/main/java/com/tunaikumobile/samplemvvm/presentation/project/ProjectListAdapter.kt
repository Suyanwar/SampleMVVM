package com.tunaikumobile.samplemvvm.presentation.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tunaikumobile.samplemvvm.R
import com.tunaikumobile.samplemvvm.databinding.ItemProjectBinding
import com.tunaikumobile.samplemvvm.model.Project


/**
 *
 * Created by Suyanwar on 2019-12-15.
 * Android Engineer
 *
 **/
class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    private lateinit var projectList: List<Project>

    class ViewHolder(private val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = ProjectViewModel()

        fun bind(project: Project) {
            viewModel.bind(project)
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemProjectBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_project,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::projectList.isInitialized) projectList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    fun updateProjectList(projectList: List<Project>) {
        this.projectList = projectList
        notifyDataSetChanged()
    }
}