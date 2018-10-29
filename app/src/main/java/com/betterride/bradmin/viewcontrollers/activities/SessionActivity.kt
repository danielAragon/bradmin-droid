package com.betterride.bradmin.viewcontrollers.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Session

import kotlinx.android.synthetic.main.activity_session.*
import kotlinx.android.synthetic.main.content_session.*

class SessionActivity : AppCompatActivity() {

    lateinit var sess : Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent ?: return
        sess = Session.from(intent.extras)
        dateSesTextView.text = sess.date
        startTimeSesTextView.text = sess.started_at
        finTimeSesTextView.text = sess.finished_at
        firstAvenTextView.text = sess.avenue_first
        secondAvenTextView.text = sess.avenue_second

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_project, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.editAction -> {
                /*startActivity(
                    Intent(applicationContext, EditProjectActivity::class.java).
                        putExtras(proj.toBundle()))*/
                return true
            }
            R.id.deleteAction -> {
                alertDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertDialog(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Delete project?")
        dialogbuilder.setPositiveButton("DELETE",
            { dialogInterface: DialogInterface, i: Int ->
                startActivity(Intent(applicationContext, ProjectActivity::class.java))

            })
        dialogbuilder.setNegativeButton("CANCEL",
            { dialogInterface: DialogInterface, i: Int ->

            })
        val alertDialog = dialogbuilder.create()
        alertDialog.show()

    }
}
