package com.betterride.bradmin.models

import android.os.Bundle

data class Operator(val name: String,
                    val last_name: String,
                    val email: String,
                    val photo: String,
                    val username: String,
                    val num_session: Int,
                    val num_ses_pend: Int,
                    val last_session: String
)
{
    companion object {
        fun from(bundle: Bundle): Operator {
            return Operator(
                bundle.getString("name")!!,
                bundle.getString("last_name")!!,
                bundle.getString("email")!!,
                bundle.getString("photo")!!,
                bundle.getString("username")!!,
                bundle.getInt("num_session")!!,
                bundle.getInt("num_ses_pend")!!,
                bundle.getString("last_session")!!
            )
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("last_name", last_name)
        bundle.putString("email", email)
        bundle.putString("photo", photo)
        bundle.putString("username", username)
        bundle.putInt("num_session", num_session)
        bundle.putInt("num_ses_pend", num_ses_pend)
        bundle.putString("last_session", last_session)
        return bundle
    }
}