package com.betterride.bradmin.viewcontrollers.fragments


import android.os.Bundle

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.betterride.bradmin.R
import com.betterride.bradmin.models.Session
import com.betterride.bradmin.network.ResponseSession
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry


class AnalyticsFragment : Fragment() {
    var sessions = ArrayList<Session>()
    var data = listOf(1,2,3,4,5,6,7)
    lateinit var sessionPieChart: PieChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_analytics, container, false)
        sessionPieChart = view.findViewById(R.id.sessionPieChart)
        return view
    }

    private fun loadPieChart() {
        sessionPieChart.setUsePercentValues(true)
        sessionPieChart.description.isEnabled = false
        sessionPieChart.setExtraOffsets(5f,10f,5f,5f)
        sessionPieChart.dragDecelerationFrictionCoef = 0.99f
        sessionPieChart.isDrawHoleEnabled = false
        sessionPieChart.transparentCircleRadius = 61f


    }
    private fun handleResponse(response: ResponseSession?){
        //Log.d("BradminApp", "Found ${response!!.data!![0].name}")
        sessions = response!!.sessions!!
        for (session in sessions) TODO("Fetch data of counting sessions")
    }


}
