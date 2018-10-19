package com.betterride.bradmin.models

import android.os.Bundle

data class User(
    val name: String,
    val email: String,
    val position: String,
    val photo: String) {
    companion object {
        fun from(bundle: Bundle): User {
            return User(
                bundle.getString("name")!!,
                bundle.getString("email")!!,
                bundle.getString("position")!!,
                bundle.getString("photo")!!

                )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putString("email",email)
        bundle.putString("position",position)
        bundle.putString("photo",photo)
        return bundle
    }
}