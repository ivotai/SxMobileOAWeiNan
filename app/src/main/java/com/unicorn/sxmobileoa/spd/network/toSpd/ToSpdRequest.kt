package com.unicorn.sxmobileoa.spd.network.toSpd

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.simple.main.model.Menu

class ToSpdRequest(menu: Menu, param: Param,type:String) : MaybeRequest("tospd") {

    init {
        addParameter(Key.moduleCode, menu.moduleCode)
        addParameter(Key.primaryId, param.primaryId)
        addParameter(Key.taskId, param.taskId)
        addParameter(Key.nodeId, param.nodeId)
        addParameter(Key.type, type)
    }

}