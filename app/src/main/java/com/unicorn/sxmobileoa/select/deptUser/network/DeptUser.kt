package com.unicorn.sxmobileoa.select.deptUser.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.select.deptUser.model.User

class DeptUser(deptId: String) : BaseUseCase<List<User>>() {

    init {
        request = DeptUserRequest(deptId)
    }

    override fun toResult(json: String): List<User> {
        val type = object : TypeToken<List<User>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<User>>(json, type)
    }

}