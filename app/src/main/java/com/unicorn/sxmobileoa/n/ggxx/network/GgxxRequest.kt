package com.unicorn.sxmobileoa.n.ggxx.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.PageRequest

class GgxxRequest(pageNo: Int, isRead: String) : PageRequest(busiCode = "ggxx", pageNo = pageNo) {

    init {
        addParameter(Key.isRead, isRead)
    }

}