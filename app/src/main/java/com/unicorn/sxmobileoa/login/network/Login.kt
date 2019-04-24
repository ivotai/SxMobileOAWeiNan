package com.unicorn.sxmobileoa.login.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo

class Login(username: String, password: String) : BaseUseCase<LoginInfo>() {

    init {
        request = LoginRequest(username,password)
    }

    override fun toResult(json: String): LoginInfo {
        return ComponentHolder.appComponent.getGson().fromJson(json, LoginInfo::class.java)
    }

}