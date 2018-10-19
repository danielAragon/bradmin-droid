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
import com.betterride.bradmin.models.Operator
import com.betterride.bradmin.models.User
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.OperatorsResponse
import com.betterride.bradmin.network.UserResponse
import com.betterride.bradmin.viewcontrollers.adapters.OperatorAdapters
import kotlinx.android.synthetic.main.fragment_operators.view.*

class OperatorsFragment : Fragment() {
    lateinit var operatorsRecyclerView: RecyclerView
    lateinit var operators: ArrayList<Operator>
    lateinit var users: ArrayList<User>
    lateinit var operatorsAdapter: OperatorAdapters
    lateinit var operatorsLayoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operators, container, false)
        operatorsRecyclerView = view.operatorsRecyclerView
        operators = ArrayList()
        users = ArrayList()
        operatorsAdapter = OperatorAdapters(operators,users,view.context)
        operatorsLayoutManager = GridLayoutManager(view.context,1) as RecyclerView.LayoutManager
        operatorsRecyclerView.adapter = operatorsAdapter
        operatorsRecyclerView.layoutManager = operatorsLayoutManager
        BRApi.requestOperators(
            { response -> handleResponse(response)},
            { error -> handleError(error)})
        BRApi.requestUsers("c13d6597335a44eafcfe09df6430e4",
            { response -> handleResponse1(response)},
            { error -> handleError1(error)})
        return view
    }
    private fun handleResponse(response: OperatorsResponse?){
        val status = response!!.status
        if (status.equals("error", true)) {
            return
        }
        operators = response.operators!!
        Log.d("BradminApp", "Found ${operators.size} projects")
        operatorsAdapter.operators = operators
        operatorsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }
    private fun handleResponse1(response: UserResponse?){
        users = response!!.users!!
        Log.d("BradminApp", "Found ${users.size} projects")
        operatorsAdapter.users = users
        operatorsAdapter.notifyDataSetChanged()
    }

    private fun handleError1(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }

}

