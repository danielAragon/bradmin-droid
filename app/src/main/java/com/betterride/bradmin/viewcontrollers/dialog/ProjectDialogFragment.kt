package com.betterride.bradmin.viewcontrollers.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.view.*
import android.widget.Toast
import com.betterride.bradmin.R
import kotlinx.android.synthetic.main.dialog_new_project.*
import java.util.*


class ProjectDialogFragment : DialogFragment() {

    var yearInput: Int =0
    var monthInput: Int =0
    var dayInput: Int =0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_new_project, container, false)

        val tol = view.findViewById<Toolbar>(R.id.toolbar)
        var dateProjEditText = view.findViewById<TextInputEditText>(R.id.dateProjEditText)
        var calendar = Calendar.getInstance()

        tol.setTitle(getString(R.string.new_project))
        (activity as AppCompatActivity).setSupportActionBar(tol)
        var actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        dateProjEditText.inputType = InputType.TYPE_NULL
        dateProjEditText.setOnClickListener { view ->
            var datePicker = DatePickerDialog(context, DatePickerDialog.OnDateSetListener {
                    view, yearT, monthT, dayOfMonthT ->
                yearInput = yearT
                monthInput = monthT
                dayInput = dayOfMonthT
                dateProjEditText.setText("$dayInput/$monthInput/$yearInput")
            }, year, month,day)
            datePicker.show()
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.saveAction -> {
                validateInput()
                return true
            }
            android.R.id.home -> {
                dismiss()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun validateInput(){
        if(!validateEditText(nameProjEditText, nameProjTextInput, getString(R.string.error_name_proj))) {

        }
        if(!validateEditText(dateProjEditText, dateProjTextInput,getString(R.string.error_proj_date))){
            return
        }
        Toast.makeText(context, "It was saved correctly", Toast.LENGTH_SHORT).show()
    }

    fun validateEditText(editText: TextInputEditText,
                         textInputLayout: TextInputLayout,
                         errorString: String): Boolean {
        if(editText.text.toString().trim().isEmpty()){
            textInputLayout.error = errorString
            return false
        }else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }
}