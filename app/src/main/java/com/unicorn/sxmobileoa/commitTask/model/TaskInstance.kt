package com.unicorn.sxmobileoa.commitTask.model

data class TaskInstance(
        val processInstanceId: String,
        val taskId: String,
        val taskPerson: TaskPerson,
        val nextTaskPersonModel: List<NextTaskPersonModel>,
        val approved: Boolean,
        val catagoryCode: String,
        val approveConent: String,
        val spdId: String,
        val fydm: String,
        val moduleCode: String,
        val flowCode: String,
        val nodeId: String,
        val spyjId: String,
        val gd: Int,
        val nextTaskKey: String,
        val tasktype: String,
        val spdCode: String,
        val endTaskType: String,
        val reject: String,
        val sfjsjd: Boolean,
        val tzstate: Boolean,
        val buinessCallBackParams: BuinessCallBackParams
)

data class TaskPerson(
    val personCode: String,
    val personName: String
)

data class NextTaskPersonModel(
    val taskType: String,
    val taskDefKey: String,
    val approveType: Int,
    val nextPersonCode: String,
    val nextPersonName: String,
    val nextLine: String
)

data class BuinessCallBackParams(
    val tdyy: String
)