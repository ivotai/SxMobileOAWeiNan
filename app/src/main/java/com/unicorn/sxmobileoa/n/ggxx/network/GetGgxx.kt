package com.unicorn.sxmobileoa.n.ggxx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx

class GetGgxx(pageNo: Int, isRead: String) : BaseUseCase<Page<Ggxx>>() {

    init {
        request = GgxxRequest(pageNo, isRead)
    }

    override fun toResult(json: String): Page<Ggxx> {
        val type = object : TypeToken<Page<Ggxx>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Ggxx>>(json, type)
    }

}