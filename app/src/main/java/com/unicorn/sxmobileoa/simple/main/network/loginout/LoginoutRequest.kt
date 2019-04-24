package com.unicorn.sxmobileoa.simple.main.network.loginout

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class LoginoutRequest : MaybeRequest("loginout") {

    init {
        addParameter(Key.ticket, Global.ticket)
    }

}
