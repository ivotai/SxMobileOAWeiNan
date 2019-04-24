package com.unicorn.sxmobileoa.n.add.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd

class AddSpd(spdCode:String) : BaseUseCase<Spd>() {

    init {
        request = AddSpdRequest(spdCode)
    }

    override fun toResult(json: String): Spd {
        return ComponentHolder.appComponent.getGson().fromJson(json, Spd::class.java)
    }

}