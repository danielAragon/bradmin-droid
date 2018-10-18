package com.betterride.bradmin.viewcontrollers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Project
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.content_project.*

class ProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent ?: return
        val project = Project.from(intent.extras)
        projectNameTextView.text = project.name
        dateProjectTextView.text = project.date
    }

}
