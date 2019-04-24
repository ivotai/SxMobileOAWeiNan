package com.unicorn.sxmobileoa.app.di

object ComponentHolder {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}