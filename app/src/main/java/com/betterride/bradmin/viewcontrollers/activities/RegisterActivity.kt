package com.betterride.bradmin.viewcontrollers.activities

import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.androidnetworking.error.ANError
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Organization
import com.betterride.bradmin.models.Supervisor
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.ResponseBasic

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {
    var check: Boolean = false;
    lateinit var organizacion: Organization;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        butonRegistrar.setOnClickListener { view ->
            var name = NameText.text.toString();
            var last_name = LastNameText.text.toString();
            var email = EmailText.text.toString();
            var username = UsernameText.text.toString();
            var password = PasswordText.text.toString();
            var genre = "";
            var token: String = TokenText.text.toString();
            if (RButtonMale.isChecked()) genre = "Male"
            else genre = "Female"

            if (!name.equals("") && !last_name.equals("") && !email.equals("")
                && !username.equals("") && !username.equals("") && !token.equals("")
            ) {
                BRApi.requestPostOrganization(token,
                    { response -> check = response!! },
                    { error ->  Log.d("BradminApp", error!!.message) }
                )
                if(check){
                    BRApi.requestPostOrganizationData(token, { response ->
                        check = response!!.message == "Ok"
                        if(check) organizacion = response.data!!
                    },
                        { error ->  Log.d("BradminApp", error!!.message)
                        check =false})
                }
                if(check){
                    BRApi.requestPostSupervisor(name,last_name,email,username,password,organizacion.id,"sup",genre,
                        {response->handleResponse(response)},
                        {error->handleError(error)})}
                }
            }

    }
    private fun handleResponse(response:ResponseBasic?){
        Log.d("BradminApp", response!!.message)
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }

}
