package com.betterride.bradmin.models

import android.os.Bundle

data class Project(
    val date: String,
    val name: String) {
    companion object {
        fun from(bundle: Bundle): Project {
            return Project(
                bundle.getString("date")!!,
                bundle.getString("name")!!
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("date", date)
        bundle.putString("name", name)
        return bundle
    }
}