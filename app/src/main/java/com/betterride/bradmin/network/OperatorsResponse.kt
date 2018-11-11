package com.betterride.bradmin.network

import com.betterride.bradmin.models.Operator

class OperatorsResponse {
    var status: String = ""
    var code: String? = null
    var message: String? = null
    var operators: ArrayList<Operator>? = null
}