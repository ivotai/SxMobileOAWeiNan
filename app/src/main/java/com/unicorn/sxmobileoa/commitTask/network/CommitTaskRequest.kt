package com.unicorn.sxmobileoa.commitTask.network

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.commitTask.model.TaskInstance

class CommitTaskRequest(taskInstance: TaskInstance) : MaybeRequest("commitTask") {

    init {
        addParameter("userJson", ComponentHolder.appComponent.getGson().toJson(Global.loginInfo))
        addParameter("taskInstance", ComponentHolder.appComponent.getGson().toJson(taskInstance))
    }

}