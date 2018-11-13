package com.betterride.bradmin.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.models.Session
import org.json.JSONException
import org.json.JSONObject

class BRApi {
    companion object {
        val baseUrl = "http://demo4638714.mockable.io/"
        val projects = "$baseUrl/projects"
        val operators = "http://demo5617161.mockable.io//operators"
        val sessions = "$baseUrl/sessions"

        val baseUrl2 = "https://srv-desa.eastus2.cloudapp.azure.com/appbetterride/api"
        val supervisor = "$baseUrl2/v1/supervisors"
        val user = "$baseUrl2/v1/login/user"
        val user2 = "$baseUrl2/user"
        val organization = "$baseUrl2/v1/organizations"

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
        fun requestPostSupervisor(name: String,lastname: String,email: String,username: String,
                                  password: String,organization_id: String,role: String,genre: String, token: String,
                                  responseHandler: (ResponseBasic?) -> Unit,
                                  errorHandler: (ANError?) -> Unit
        ) {
            val data = JSONObject()
            try {
                data.put("name", name)
                data.put("last_name", lastname)
                data.put("email", email)
                data.put("username", username)
                data.put("password", password)
                data.put("organization_id", organization_id)
                data.put("role", role)
                data.put("gender", genre)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(BRApi.supervisor)
                .addHeaders("token", token)
                .addBodyParameter(data)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseBasic::class.java, object : ParsedRequestListener<ResponseBasic>{
                    override fun onResponse(response: ResponseBasic?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostSupervisorValidate(username: String,
                                          password: String,
                                          responseHandler: (ResponseSupervisor?) -> Unit,
                                          errorHandler: (ANError?) -> Unit
        ) {

            AndroidNetworking.post("https://srv-desa.eastus2.cloudapp.azure.com/appbetterride/api/v1/login/user/$username/pass/$password")
                .addHeaders("token", "1234")
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseSupervisor::class.java, object : ParsedRequestListener<ResponseSupervisor>{
                    override fun onResponse(response: ResponseSupervisor?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostOrganization(token: String,
                                    responseHandler: (Boolean?) -> Unit,
                                    errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.post(BRApi.user2)
                .addHeaders("token", token)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(Boolean::class.java, object : ParsedRequestListener<Boolean>{
                    override fun onResponse(response: Boolean?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
        fun requestPostOrganizationData(token: String,
                                        responseHandler: (ResponseOrganization?) -> Unit,
                                        errorHandler: (ANError?) -> Unit
        ) {
            AndroidNetworking.post(BRApi.organization)
                .addHeaders("token", token)
                .setPriority(Priority.LOW)
                .setTag("BradminApp")
                .build()
                .getAsObject(ResponseOrganization::class.java, object : ParsedRequestListener<ResponseOrganization>{
                    override fun onResponse(response: ResponseOrganization?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
    }
}
