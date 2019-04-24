package com.unicorn.sxmobileoa.header.ycsq.cllx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.header.ycsq.cllx.model.CllxGroup

class GetCllx : BaseUseCase<List<CllxGroup>>() {

    init {
        request = CllxRequest()
    }

    override fun toResult(json: String): List<CllxGroup> {
        val type = object : TypeToken<List<CllxGroup>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<CllxGroup>>(json, type)
    }

}
