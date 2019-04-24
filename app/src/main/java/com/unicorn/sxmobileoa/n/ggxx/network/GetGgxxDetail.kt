package com.unicorn.sxmobileoa.n.ggxx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.ggxx.model.GgxxDetail

class GetGgxxDetail(id:  String) : BaseUseCase<GgxxDetail>() {

    init {
        request = GgxxDetialRequest(id)
    }

    override fun toResult(json: String): GgxxDetail {
        val type = object : TypeToken<GgxxDetail>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<GgxxDetail>(json, type)
    }

}