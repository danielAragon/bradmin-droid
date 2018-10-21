package com.betterride.bradmin.models

import android.os.Bundle

data class Project(
    val id: String,
    val date: String? = "",
    val name: String? = "") {
    companion object {
        fun from(bundle: Bundle): Project {
            return Project(
                bundle.getString("id"),
                bundle.getString("date"),
                bundle.getString("name")
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("date", date)
        bundle.putString("name", name)
        return bundle
    }
}