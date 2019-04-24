package com.unicorn.sxmobileoa.spd.network.spdZw

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

class SpdZw(spdId:String) : BaseUseCase<SpdZwResponse>() {

    init {
        request = SpdZwRequest(spdId)
    }

    override fun toResult(json: String): SpdZwResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SpdZwResponse::class.java)
    }

}