package com.betterride.bradmin.models

import android.os.Bundle

data class Operator(val name: String,
                    val lastName: String,
                    val email: String,
                    val userName: String,
                    val photo: String
)
{
    companion object {
        fun from(bundle: Bundle): Operator {
            return Operator(
                bundle.getString("name")!!,
                bundle.getString("lastName")!!,
                bundle.getString("email")!!,
                bundle.getString("userName")!!,
                bundle.getString("photo")!!

            )
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("lastName", lastName)
        bundle.putString("email", email)
        bundle.putString("userName", userName)
        bundle.putString("photo", photo)
        return bundle
    }
}