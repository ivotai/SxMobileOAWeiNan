package com.unicorn.sxmobileoa.n.csx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.csx.model.Csx

class GetSpcsx : BaseUseCase<List<Csx>>() {

    init {
        request = SpcsxRequest()
    }

    override fun toResult(json: String): List<Csx> {
        val type = object : TypeToken<List<Csx>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Csx>>(json, type)
    }

}