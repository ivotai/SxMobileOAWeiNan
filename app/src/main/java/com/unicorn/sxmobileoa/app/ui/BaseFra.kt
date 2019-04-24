package com.unicorn.sxmobileoa.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

abstract class BaseFra : SupportFragment(), ActOrFra {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initArguments()
        initViews()
        registerEvent()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        bindIntent()
    }

    override fun initArguments() {
    }

    override fun registerEvent() {
    }

}