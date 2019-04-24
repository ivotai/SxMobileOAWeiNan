package com.unicorn.sxmobileoa.n.add.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

open class AddSpdRequest(spdCode: String) : MaybeRequest("addspd") {

    init {
        addParameter(Key.spdCode, spdCode)
    }

}
