package com.unicorn.sxmobileoa.n.news.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.news.model.News

class GetNews() : BaseUseCase<List<News>>() {

    init {
        request = NewsRequest()
    }

    override fun toResult(json: String): List<News> {
        val type = object : TypeToken<List<News>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<News>>(json, type)
    }

}