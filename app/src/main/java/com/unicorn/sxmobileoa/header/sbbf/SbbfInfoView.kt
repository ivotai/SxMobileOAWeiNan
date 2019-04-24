package com.unicorn.sxmobileoa.header.sbbf

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.header.sbbf.detail.SbbfDetailAct
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_sbbf.view.*

@SuppressLint("ViewConstructor")
class SbbfInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd)
    }

    @SuppressLint("CheckResult")
    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbbf, this, true)
        preparePairs()
        renderView(menu, spd)
        tvSbbfDetail.safeClicks().subscribe {
            context.startActivity(Intent(context, SbbfDetailAct::class.java))
        }
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqbm, Key.sqbm_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqsj, Key.sqsj_input))
            add(PAIR(tvBfxq, Key.bfxq_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
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