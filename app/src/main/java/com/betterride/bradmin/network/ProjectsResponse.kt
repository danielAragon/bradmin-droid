package com.betterride.bradmin.network

import com.betterride.bradmin.models.Project


class ProjectsResponse {
    var status: String = ""
    var organizationName: String = ""
    var projects: ArrayList<Project>? = null
}