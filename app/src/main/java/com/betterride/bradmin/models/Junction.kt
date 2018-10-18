package com.betterride.bradmin.models

import android.os.Bundle

data class Junction(
    val project: Project,
    val id: String,
    val name: String){
    companion object {
        fun from(bundle: Bundle): Junction {
            return Junction(
                Project.from(bundle.getBundle("project")!!),
                bundle.getString("id")!!,
                bundle.getString("name")!!
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putBundle("project", project.toBundle())
        bundle.putString("id", id)
        bundle.putString("name", name)
        return bundle
    }
}