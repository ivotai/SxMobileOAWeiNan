package com.unicorn.sxmobileoa.app.network.model

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.config.ConfigModule

open class MaybeRequest(busiCode: String) : request(busiCode) {

    init {
        addParameter(Key.fydm, (Global.court?.dm ?: ConfigModule.defaultFydm))
        Global.loginInfo?.userId?.let { addParameter(Key.userId, it) }
    }

}
