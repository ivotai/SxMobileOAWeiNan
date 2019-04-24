package com.unicorn.sxmobileoa.sequenceFlow.network.spdNext

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.sequenceFlow.model.SpdNextResponse
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.spd.model.Spd

class SpdNext( param: Param,spd:Spd) : BaseUseCase<SpdNextResponse>() {

    init {
        request = SpdNextRequest( param, spd)
    }

    override fun toResult(json: String): SpdNextResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SpdNextResponse::class.java)
    }

}