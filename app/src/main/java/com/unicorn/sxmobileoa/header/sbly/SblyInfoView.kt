package com.unicorn.sxmobileoa.header.sbly

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.header.sbly.detail.SblyDetailAct
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_sbly.*
import org.joda.time.DateTime

@SuppressLint("ViewConstructor")
class SblyInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    @SuppressLint("CheckResult")
    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbly, this, true)
        preparePairs()
        renderView(menu, spd, isCreate)
        tvSblyDetail.safeClicks().subscribe {
            context.startActivity(Intent(context, SblyDetailAct::class.java).apply {
                putExtra(Key.isCreate, isCreate)
            })
        }
        canEdit(spd, isCreate)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqbm, Key.sqbm_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqsj, Key.sqsj_input))
            add(PAIR(tvLyxq, Key.lyxq_input))
            add(PAIR(tvLyrq, Key.lyrq_date))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd, isCreate: Boolean) {
        // 新增时有些值需要赋值
        if (isCreate) {
            spd.spdXx.apply {
                spd.set(Key.sqsj_input, DateTime().toString("yyyy-MM-dd"))
                spd.set(Key.sqr_input, createUserName)
                spd.set(Key.sqbm_input, bmmc)
            }
        }

        // 展示值
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.setText(spd.spdXx.bt)
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd, isCreate: Boolean) {
        if (isCreate) {
            tvBt.isEnabled = true
            tvSqsj.clickDate(false)
            tvLyxq.isEnabled = true
        }
        val nodeId = spd.nodeModel_1!!.nodeid
        if (nodeId in listOf("OA_FLOW_XZZB_SBLY_CGR", "OA_FLOW_XZZB_SBLY_BGS", "OA_FLOW_XZZB_SBLY_KGY")) tvLyrq.clickDate(false)
    }

    override fun saveToSpd(spd: Spd): Boolean {
        if (tvBt.trimText().isEmpty()) {
            ToastUtils.showShort("标题不能为空")
            return false
        }
        spd.spdXx.apply {
            bt = tvBt.trimText()
        }
        pairs.forEach { pair ->
            pair.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}