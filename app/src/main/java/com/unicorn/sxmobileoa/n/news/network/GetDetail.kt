package com.unicorn.sxmobileoa.n.news.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.news.model.News

class GetDetail(contentId:String) : BaseUseCase<News>() {

    init {
        request = NewsDetailRequest(contentId)
    }

    override fun toResult(json: String): News {
        val type = object : TypeToken<News>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<News>(json, type)
    }

}