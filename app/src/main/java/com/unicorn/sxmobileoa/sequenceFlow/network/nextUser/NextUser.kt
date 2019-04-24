package com.unicorn.sxmobileoa.sequenceFlow.network.nextUser

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spd.model.Spd

class NextUser(spd: Spd, nextTaskSequenceFlow: NextTaskSequenceFlow) : BaseUseCase<MutableList<User>>() {

    init {
        request = NextUserRequest(spd, nextTaskSequenceFlow)
    }

    override fun toResult(json: String): MutableList<User> {
        val type = object : TypeToken<MutableList<User>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<MutableList<User>>(json, type)
    }
}