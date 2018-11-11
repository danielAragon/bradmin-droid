package com.betterride.bradmin.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.models.Session

class BRApi {
    companion object {
        val baseUrl = "http://demo4638714.mockable.io/"
        val projects = "$baseUrl/projects"
        val operators = "http://demo5617161.mockable.io//operators"
        val sessions = "$baseUrl/sessions"

        fun requestGetProjects(
            responseHandler: (ArrayList<Project>?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.projects)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObjectList(Project::class.java, object : ParsedRequestListener<ArrayList<Project>> {
                    override fun onResponse(response: ArrayList<Project>?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

        fun requestOperators(
            responseHandler: (OperatorsResponse?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.operators)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(OperatorsResponse::class.java, object : ParsedRequestListener<OperatorsResponse> {
                    override fun onResponse(response: OperatorsResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }



        fun requestGetSessions(
            responseHandler: (ArrayList<Session>?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ){
            AndroidNetworking.get(BRApi.sessions)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObjectList(Session::class.java, object : ParsedRequestListener<ArrayList<Session>> {
                    override fun onResponse(response: ArrayList<Session>?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }

                })
        }
    }
}
