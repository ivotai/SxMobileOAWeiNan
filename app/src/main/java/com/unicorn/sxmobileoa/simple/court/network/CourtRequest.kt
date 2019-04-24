package com.unicorn.sxmobileoa.simple.court.network

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.network.model.request

class CourtRequest : request("fyxx") {

    init {
        addParameter(Key.fydm, (ConfigModule.defaultFydm))
        Global.loginInfo?.userId?.let { addParameter(Key.userId, it) }
        // 查询所有法院     0
        // 中院和基层法院   2
        addParameter("type", "2")
    }

}
