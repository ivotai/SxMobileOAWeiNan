package com.unicorn.sxmobileoa.app.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideUniqueApi(retrofit: Retrofit): UniqueApi = retrofit.create(UniqueApi::class.java)

}