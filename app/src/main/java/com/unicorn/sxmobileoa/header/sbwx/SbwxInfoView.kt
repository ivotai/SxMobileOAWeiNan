package com.unicorn.sxmobileoa.header.sbwx

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_sbwx.view.*

@SuppressLint("ViewConstructor")
class SbwxInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbwx, this, true)
        preparePair()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun preparePair() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvJbdw, Key.jbdw_input))
            add(PAIR(tvJbr, Key.jbr_input))
            add(PAIR(tvSj, Key.sj_input))
            add(PAIR(tvWxxq, Key.wxxq_textarea))
            add(PAIR(tvWxff, Key.wxff_textarea))
            add(PAIR(tvWxysjf, Key.wxysjf_input))
            add(PAIR(tvCljg, Key.cljg_input))
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

    private fun canEdit(spd: Spd) {
        val nodeId = spd.nodeModel_1!!.nodeid
        val canEdit = nodeId in listOf("OA_FLOW_XZZB_SBWX_XXZXYJ", "OA_FLOW_XZZB_SBWX_XXZXBL", "OA_FLOW_XZZB_SBWX_WXRY")
        tvCljg.isEnabled = canEdit
    }

    override fun saveToSpd(spd: Spd): Boolean {
        pairs.forEach { pair ->
            pair.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}