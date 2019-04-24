package com.unicorn.sxmobileoa.header.swgl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_swgl.view.*

@SuppressLint("ViewConstructor")
class SwglnfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd,isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_swgl, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvBlsx, Key.blsx_input))
            add(PAIR(tvHj, Key.hjcd_select))
            add(PAIR(tvMj, Key.mjcd_select))
            add(PAIR(tvBzxx, Key.remark_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.apply {
            tvBt.text = bt
            tvLwdw.text = column3
            tvSwbh.text = sdwh
            tvLwwh.text = column1
            tvQsr.text = column4
            tvLwrq.text = column2
        }
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    override fun saveToSpd(spd: Spd): Boolean {
        return true
    }

}