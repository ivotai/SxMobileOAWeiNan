package com.unicorn.sxmobileoa.n.csx.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class CsxRequest(val type: Int) : MaybeRequest(busiCode = "csxtip") {

    init {
        addParameter(Key.type, type.toString())
    }

}

