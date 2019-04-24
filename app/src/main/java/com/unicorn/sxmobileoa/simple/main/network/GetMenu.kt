package com.unicorn.sxmobileoa.simple.main.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.main.model.Menu

class GetMenu : BaseUseCase<List<Menu>>() {

    init {
        request = MenuRequest()
    }

    override fun toResult(json: String): List<Menu> {
        val type = object : TypeToken<List<Menu>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Menu>>(json, type)
    }

}