package com.betterride.bradmin.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Operator
import com.betterride.bradmin.viewcontrollers.activities.EditOperatorActivity
import com.betterride.bradmin.viewcontrollers.activities.OperatorActivity
import kotlinx.android.synthetic.main.item_operator.view.*

class OperatorAdapters(var operators: ArrayList<Operator> ,val context: Context) :
    RecyclerView.Adapter<OperatorAdapters.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_operator, parent, false))
    }

    override fun getItemCount(): Int {
        return operators.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val operator = operators.get(position)
        holder.updateFrom(operator)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameOperatorTextView = view.nameOperatorTextView
        val userNameTextView = view.userNameTextView
        val operatorImageView = view.operatorImageView
        val itemOperator = view.item_operator
        fun updateFrom(operator: Operator) {
            nameOperatorTextView.text = operator.name
            userNameTextView.text = operator.userName
            operatorImageView.setImageUrl(operator.photo)
            itemOperator.setOnClickListener { view ->
                val context = view.context
                context.startActivity(
                        Intent(context, OperatorActivity::class.java)
                                .putExtras(operator.toBundle()))

            }

        }
    }
}