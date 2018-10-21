package com.betterride.bradmin.models

import android.os.Bundle

data class Junction(
    val project: Project,
    val id: String,
    val name: String,
    val avenueFirst: String,
    val avenueSecond: String){
    companion object {
        fun from(bundle: Bundle): Junction {
            return Junction(
                Project.from(bundle.getBundle("project")!!),
                bundle.getString("id")!!,
                bundle.getString("name")!!,
                bundle.getString("avenueFirst")!!,
                bundle.getString("avenueSecond")!!
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putBundle("project", project.toBundle())
        bundle.putString("id", id)
        bundle.putString("name", name)
        bundle.putString("avenueFirst", avenueFirst)
        bundle.putString("avenueSecond", avenueSecond)
        return bundle
    }
}