package com.betterride.bradmin.viewcontrollers.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Junction
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.JunctionsResponse
import com.betterride.bradmin.viewcontrollers.adapters.JunctionsAdapter
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.content_project.*

class ProjectActivity : AppCompatActivity() {
    var junctions = ArrayList<Junction>()
    lateinit var proj: Project
    lateinit var junctionsRecyclerView: RecyclerView
    lateinit var junctionsAdapter: JunctionsAdapter
    lateinit var junctionsLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent ?: return
        proj = Project.from(intent.extras)
        projectNameTextView.text = proj.name
        dateProjectTextView.text = proj.date


        junctionsRecyclerView = juncRecyclerView
        junctionsAdapter = JunctionsAdapter(junctions, this)
        junctionsLayoutManager = GridLayoutManager(this, 1)
        junctionsRecyclerView.adapter = junctionsAdapter
        junctionsRecyclerView.layoutManager = junctionsLayoutManager

        BRApi.requestGetJunctions(
            {response -> handleResponse(response)},
            {error -> handleError(error)}
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_project, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.editAction -> {
                startActivity(Intent(applicationContext, EditProjectActivity::class.java).
                    putExtras(proj.toBundle()))
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
                startActivity(Intent(applicationContext, MainActivity::class.java))

            })
        dialogbuilder.setNegativeButton("CANCEL",
            { dialogInterface: DialogInterface, i: Int ->

            })
        val alertDialog = dialogbuilder.create()
        alertDialog.show()

    }

    private fun handleResponse(response: JunctionsResponse?){
        val status = response!!.status
        if(status.equals("error",true)){
            Log.d("CotchUp", "Error")
            return
        }
        junctions = response.junctions!!
        Log.d("BradminApp", "Found ${junctions.size} junctions")
        junctionsAdapter.junctions = junctions
        junctionsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }
}
