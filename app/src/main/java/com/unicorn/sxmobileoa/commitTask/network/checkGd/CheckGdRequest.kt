package com.unicorn.sxmobileoa.commitTask.network.checkGd

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class CheckGdRequest(processInstancesId: String, taskId: String) : MaybeRequest("validDbData") {

    init {
        addParameter(Key.processInstanceId, processInstancesId)
        addParameter(Key.taskId, taskId)
    }

}