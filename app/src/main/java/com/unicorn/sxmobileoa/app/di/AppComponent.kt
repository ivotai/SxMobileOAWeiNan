package com.unicorn.sxmobileoa.app.di

import com.google.gson.Gson
import com.unicorn.sxmobileoa.app.api.ApiModule
import com.unicorn.sxmobileoa.app.api.UniqueApi
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.network.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ConfigModule::class, RetrofitModule::class, ApiModule::class])
interface AppComponent {

    fun getGson(): Gson

    fun getUniqueApi(): UniqueApi

}