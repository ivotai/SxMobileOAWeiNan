package com.unicorn.sxmobileoa.commitTask.network.checkGd

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class CheckGd(processInstanceId:String,taskId:String):BaseUseCase<CheckGdResponse>(){

    init {
        request = CheckGdRequest(processInstanceId,taskId)
    }

    override fun toResult(json: String): CheckGdResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json,CheckGdResponse::class.java)
    }

}