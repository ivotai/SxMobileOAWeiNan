package com.unicorn.sxmobileoa.select.code.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.select.code.model.Code

class GetCode(code: String) : BaseUseCase<List<Code>>() {

    init {
        request = CodeRequest(code)
    }

    override fun toResult(json: String): List<Code> {
        val type = object : TypeToken<List<Code>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Code>>(json, type)
    }
}