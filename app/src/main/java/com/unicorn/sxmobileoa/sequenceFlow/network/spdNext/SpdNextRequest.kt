package com.unicorn.sxmobileoa.sequenceFlow.network.spdNext

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.spd.model.Spd

class SpdNextRequest(param: Param, spd: Spd) : MaybeRequest("spdnext") {

    init {
        addParameter(Key.moduleCode, spd.spdXx.moduleCode)
        addParameter(Key.flowCode, spd.spdXx.flowCode)
        addParameter(Key.primaryId, param.primaryId)
        addParameter(Key.currentTaskId, param.taskId)
        addParameter(Key.nodeId, param.nodeId)
        addParameter(Key.processInstanceId, spd.spdXx.processInstancesId)
    }

}