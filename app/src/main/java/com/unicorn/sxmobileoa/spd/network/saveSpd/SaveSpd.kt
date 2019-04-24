package com.unicorn.sxmobileoa.spd.network.saveSpd

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd

class SaveSpd(spd:Spd) : BaseUseCase<SaveSpdResponse>() {

    init {
        request = SaveSpdRequest(spd)
    }

    override fun toResult(json: String): SaveSpdResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SaveSpdResponse::class.java)
    }

}
