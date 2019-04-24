package com.unicorn.sxmobileoa.n.process.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.process.model.Process

class GetSqlcls(processInstanceId: String) : BaseUseCase<List<Process>>() {

    init {
        request = SplclsRequest(processInstanceId)
    }

    override fun toResult(json: String): List<Process> {
        val type = object : TypeToken<List<Process>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Process>>(json, type)
    }

}