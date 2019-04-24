package com.unicorn.sxmobileoa.n.process.model

data class Process(
    val dealPersonName: String,
    val dealTime: String,
    val nextPersonName: String,
    val nextTaskName: String,
    val processInstanceId: String,
    val spResult: String,
    val startTime: String,
    val taskName: String
)