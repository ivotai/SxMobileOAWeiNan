package com.unicorn.sxmobileoa.simple.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R

class HomeHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_home, this, true)
    }

}