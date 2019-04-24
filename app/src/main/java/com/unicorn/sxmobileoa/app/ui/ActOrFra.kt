package com.unicorn.sxmobileoa.app.ui

interface ActOrFra {

    fun initArguments()

    val layoutId: Int

    fun initViews()

    fun bindIntent()

    fun registerEvent()

}