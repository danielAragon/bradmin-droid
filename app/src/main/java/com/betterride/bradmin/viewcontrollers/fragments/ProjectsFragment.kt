package com.betterride.bradmin.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError

import com.betterride.bradmin.R
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.ProjectsResponse
import com.betterride.bradmin.viewcontrollers.adapters.ProjectsAdapter
import kotlinx.android.synthetic.main.fragment_projects.view.*

class ProjectsFragment : Fragment() {

    var projects = ArrayList<Project>()
    lateinit var projectsRecyclerView: RecyclerView
    lateinit var projectsAdapter: ProjectsAdapter
    lateinit var projectsLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_projects, container, false)

        projectsRecyclerView = view.projectsRecyclerView
        projectsAdapter = ProjectsAdapter(projects, view.context)
        projectsLayoutManager = GridLayoutManager(view.context,1) as RecyclerView.LayoutManager
        projectsRecyclerView.adapter = projectsAdapter
        projectsRecyclerView.layoutManager = projectsLayoutManager

        BRApi.requestGetProjects({ response -> handleResponse(response)},
            { error -> handleError(error)})

        return view
    }

    private fun handleResponse(response: ProjectsResponse?){
        val status = response!!.status
        if (status.equals("error", true)) {
            return
        }
        projects = response.projects!!
        Log.d("BradminApp", "Found ${projects.size} projects")
        projectsAdapter.projects = projects
        projectsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }
}
