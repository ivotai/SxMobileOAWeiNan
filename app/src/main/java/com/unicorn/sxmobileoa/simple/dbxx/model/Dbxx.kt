package com.unicorn.sxmobileoa.simple.dbxx.model

import java.io.Serializable

data class Dbxx(
        val bt: String,
        val cjrq: String,
        val ngrDept: String,
        val ngrName: String,
        val nodeName: String,
        val param: Param,
        val wh: String
) : Serializable

data class Param(
        val nodeId: String,
        val primaryId: String,
        val taskId: String
) : Serializable