package com.betterride.bradmin.viewcontrollers.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Session
import kotlinx.android.synthetic.main.activity_session.*
import kotlinx.android.synthetic.main.content_session.*


class SessionActivity : AppCompatActivity() {
    lateinit var session: Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        session = Session.from(intent.extras)
        var year = session.date.subSequence(0, 4).toString()
        var month = session.date.subSequence(5, 7).toString()
        var day = session.date.subSequence(8, 10).toString()
        sessionDateTextView.text = "$day/$month/$year"
        sessionStartAtTextView.text = session.started_at
        sessionFinishAtTextView.text = session.finished_at
        sessionStatusTextView.text = if (session.status.equals("rea")) {
            "finished"
        } else "unfinished"
        sessionAvenueFirstTextView.text = session.avenue_first
        sessionAvenueSecondTextView.text = session.avenue_second

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_analytics, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.analyticsAction -> {
                startActivity(
                    Intent(applicationContext, AnalyticsActivity::class.java).
                        putExtras(session.toBundle()))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
