package com.betterride.bradmin.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Junction
import kotlinx.android.synthetic.main.item_junction.view.*

class JunctionsAdapter(var junctions: ArrayList<Junction>,
                       val context: Context) : RecyclerView.Adapter<JunctionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_junction, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return junctions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val junction = junctions.get(position)
        holder.updateFrom(junction)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleJuncTextView = view.titleJuncTextView
        val firstAvenueTextView = view.firstAvenueTextView
        val secondAvenueTextView = view.secondAvenueTextView

        fun updateFrom(junction: Junction){
            titleJuncTextView.text = junction.name
            firstAvenueTextView.text = junction.avenueFirst
            secondAvenueTextView.text = junction.avenueSecond
        }

    }
}