package com.unicorn.sxmobileoa.header.gcsq

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_gcsq.view.*

@SuppressLint("ViewConstructor")
class GcsqInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_gcsq, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSzbm, Key.szbm_input))
            add(PAIR(tvWcr, Key.mcwcr_input))
            add(PAIR(tvFzbry, Key.fzbry_input))
            add(PAIR(tvWcdd, Key.wcdd_input))
            add(PAIR(tvWcsy, Key.wcsy_textarea))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.apply {
            tvBt.text = bt
            tvKsrq.text = column3
            tvJsrq.text = column4
        }
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd) {
        val nodeId =  spd.nodeModel_1!!.nodeid
        tvBz.isEnabled = nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")
    }

    override fun saveToSpd(spd: Spd): Boolean {
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}