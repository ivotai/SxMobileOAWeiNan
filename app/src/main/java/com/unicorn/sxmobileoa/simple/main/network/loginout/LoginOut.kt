package com.unicorn.sxmobileoa.simple.main.network.loginout

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class LoginOut : BaseUseCase<Any>() {

    init {
        request = LoginoutRequest()
    }

    override fun toResult(json: String): Any {
        val type = object : TypeToken<Any>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Any>(json, type)
    }

}