package com.unicorn.sxmobileoa.login.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class LoginRequest(username: String, password: String) : MaybeRequest("oalogin") {

    init {
        addParameter("userName", username)
        addParameter("passWord", password)
    }

}
