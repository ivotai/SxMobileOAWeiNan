package com.unicorn.sxmobileoa.spd.network.spdZw

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class SpdZwRequest(spdId: String) : MaybeRequest("spdZw") {

    init {
        addParameter(Key.spdid, spdId)
    }

}