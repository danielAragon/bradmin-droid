package com.betterride.bradmin.viewcontrollers.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.betterride.bradmin.R
import com.betterride.bradmin.models.ActualSession
import com.betterride.bradmin.network.BRApi

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {
    var check: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        butonIngresar.setOnClickListener { view ->
            var username = UsernameEditText.text.toString();
            var password = PasswordEditText.text.toString();
            var token = TokenEditText.text.toString();
            if (!username.equals("") && !password.equals("") && !token.equals("")) {
                BRApi.requestPostSupervisorValidate(username,password,token,
                    { response -> check = response!!.message == "Ok"
                        ActualSession.sup=response.data
                        ActualSession.token=token},
                    { error ->  Log.d("BradminApp", error!!.message) }
                )

            }
        }
        }
}

