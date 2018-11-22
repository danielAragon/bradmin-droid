package com.betterride.bradmin.viewcontrollers.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Session
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_session.*
import kotlinx.android.synthetic.main.content_session.*


class SessionActivity : AppCompatActivity() {
    lateinit var session: Session
    override fun onCreate(savedInstanceState: Bundle?) {
        var carTypes = listOf("Pesados", "Livianos", "Públicos")
        var nCarTypes = listOf(50f, 60f, 120f)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        session = Session.from(intent.extras)
        var year = session.date.subSequence(0, 4).toString()
        var month = session.date.subSequence(5, 7).toString()
        var day = session.date.subSequence(8, 10).toString()
        sessionNameTextView.text = "Session $day/$month/$year"
        firstAvenueTextView.text = session.avenue_first
        secondAvenueTextView.text = session.avenue_second

        var entries = mutableListOf<PieEntry>()
        for (i in 0..2) {
            entries.add(PieEntry(nCarTypes[i],carTypes[i]))
        }

        var pieDataSet = PieDataSet(entries, "")
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f


        var pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)
        pieData.setValueTextColor(Color.WHITE)

        carTypesPieChart.data = pieData
        carTypesPieChart.setUsePercentValues(true)
        carTypesPieChart.description.isEnabled = false
        carTypesPieChart.setExtraOffsets(5f,0f,5f,5f)
        carTypesPieChart.isDrawHoleEnabled = true
        carTypesPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        carTypesPieChart.centerText = "Car Types"
        carTypesPieChart.setCenterTextSize(20f)


        //var entries2 = mutableListOf<BarEntry>()
        //for (i in 0..2) {
        //    entries2.add(BarEntry(i.toFloat(),nCarTypes[i]))
        //}

        //var barDataSet = BarDataSet()
        //carTypesBarChart



    }
}
