package com.betterride.bradmin.models

import android.os.Bundle

data class Project(
    val id: String,
    val name: String,
    val date: String? = "") {
    companion object {
        fun from(bundle: Bundle): Project {
            return Project(
                bundle.getString("id"),
                bundle.getString("name"),
                bundle.getString("date")
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("name", name)
        bundle.putString("date", date)
        return bundle
    }
}