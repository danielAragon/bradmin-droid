package com.betterride.bradmin.viewcontrollers.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Project

import kotlinx.android.synthetic.main.activity_edit_project.*
import kotlinx.android.synthetic.main.content_new_project.*

class EditProjectActivity : AppCompatActivity() {
    lateinit var proj: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("Edit project")

        val intent = intent ?: return
        proj = Project.from(intent.extras)
        nameProjEditText.setText(proj.name)
        dateProjEditText.setText(proj.date)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.saveAction -> {
                validateInput()
                return true
            }
            android.R.id.home -> {
                startActivity(Intent(this,ProjectActivity::class.java)
                    .putExtras(proj.toBundle()))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateInput(){
        if(!validateEditText(nameProjEditText, nameProjTextInput, getString(R.string.error_name_proj))){
        }
        if(!validateEditText(dateProjEditText, dateProjTextInput, getString(R.string.error_proj_date))){
            return
        }
        Toast.makeText(applicationContext, "It was saved correctly", Toast.LENGTH_SHORT).show()
    }

    private fun validateEditText(editText: TextInputEditText,
                                 textInputLayout: TextInputLayout,
                                 errorString: String): Boolean{
        if(editText.text.toString().trim().isEmpty()){
            textInputLayout.error = errorString
            return false
        }else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }
}
