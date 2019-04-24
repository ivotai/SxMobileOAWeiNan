package com.unicorn.sxmobileoa.spd.ui.headerView

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R

class ButtonFooterView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var btnSave:Button

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.footer_view_button, this, true)
        btnSave = findViewById(R.id.btnSave)
    }

}