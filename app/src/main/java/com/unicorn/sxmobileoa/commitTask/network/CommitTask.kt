package com.unicorn.sxmobileoa.commitTask.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskResponse
import com.unicorn.sxmobileoa.commitTask.model.TaskInstance

class CommitTask(taskInstance: TaskInstance) : BaseUseCase<CommitTaskResponse>() {

    init {
        request = CommitTaskRequest(taskInstance)
    }

    override fun toResult(json: String): CommitTaskResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, CommitTaskResponse::class.java)
    }

}
