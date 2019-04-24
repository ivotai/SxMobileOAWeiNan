package com.unicorn.sxmobileoa.n.process.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class SplclsRequest(processInstanceId: String) : MaybeRequest(busiCode = "splcls") {

    init {
        addParameter(Key.processInstanceId, processInstanceId)
    }

}