package com.unicorn.sxmobileoa.spd.network.toSpd

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

class ToSpd(menu: Menu, param: Param, type: String) : BaseUseCase<Spd>() {

    init {
        request = ToSpdRequest(menu, param, type)
    }

    override fun toResult(json: String): Spd {
        return ComponentHolder.appComponent.getGson().fromJson(json, Spd::class.java)
    }

}