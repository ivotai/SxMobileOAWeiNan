package com.unicorn.sxmobileoa.n.ggxx.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class GgxxDetialRequest(id:String) : MaybeRequest(busiCode = "ggxxDitail") {

    init {
        addParameter(Key.id, id)
    }

}