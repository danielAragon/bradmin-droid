package com.betterride.bradmin.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class BRApi {
    companion object {
        val baseUrl = "http://demo4638714.mockable.io/"
        val projects = "$baseUrl/projects"
        val operators = "http://demo5617161.mockable.io//operators"
        val users = "https://uifaces.co/api"
        val junctions = "$baseUrl/junctions"


        fun requestGetProjects(
            responseHandler: (ProjectsResponse?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.projects)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ProjectsResponse::class.java,
                    object : ParsedRequestListener<ProjectsResponse> {

                        override fun onResponse(response: ProjectsResponse?) {
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

        fun requestUsers(
            key: String, responseHandler: (UserResponse?) -> Unit,
            errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.get(BRApi.users)
                .addQueryParameter("X-API-KEY", key)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(UserResponse::class.java, object : ParsedRequestListener<UserResponse> {
                    override fun onResponse(response: UserResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

            fun requestGetJunctions(
                responseHandler: (JunctionsResponse?) -> Unit,
                errorHandler: (ANError?) -> Unit
            ) {
                AndroidNetworking.get(BRApi.junctions)
                    .setPriority(Priority.LOW)
                    .setTag("BradminApp")
                    .build()
                    .getAsObject(JunctionsResponse::class.java,
                        object : ParsedRequestListener<JunctionsResponse> {
                            override fun onResponse(response: JunctionsResponse?) {
                                responseHandler(response)
                            }

                            override fun onError(anError: ANError?) {
                                errorHandler(anError)
                            }
                        })
            }
        }
    }
