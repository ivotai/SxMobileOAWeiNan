package com.unicorn.sxmobileoa.header.qjsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_qjsq.view.*
import org.joda.time.DateTime

@SuppressLint("ViewConstructor")
class QjsqInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_qjsq, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd, isCreate)
        canEdit(spd.nodeModel_1!!.nodeid, isCreate)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvQjr, Key.qjr_input))
            add(PAIR(tvZw, Key.zw_input))
            add(PAIR(tvSzbm, Key.szbm_input))
            add(PAIR(tvQjsy, Key.qjsy_textarea))
            add(PAIR(tvXjzl, Key.xjzljsy_select))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd, isCreate: Boolean) {
        // 新增时有些值需要赋值
        if (isCreate) {
            spd.spdXx.apply {
                column2 = DateTime().toString("yyyy-MM-dd")
                spd.set(Key.qjr_input, createUserName)
                spd.set(Key.szbm_input, bmmc)
            }
        }

        // 展示值
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.setText(spd.spdXx.bt)
        tvSqrq.text = spd.spdXx.column2
        tvKsrq.text = spd.spdXx.column3
        tvJsrq.text = spd.spdXx.column4
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(nodeId: String, isCreate: Boolean) {
        if (isCreate) {
            tvBt.isEnabled = true
            tvSqrq.clickDate()
            tvZw.isEnabled = true
            tvQjsy.isEnabled = true
            tvXjzl.clickCode("休假种类及年度", "QJSQ_JQ", Key.xjzljsy_select)
            tvKsrq.clickDate()
            tvJsrq.clickDate()
            RxBus.get().registerEvent(CodeResult::class.java, context as LifecycleOwner, Consumer {
                // somehow val equals text
                tvXjzl.text = it.result.text
            })
        }

        // 特殊字段
        tvBz.isEnabled = nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")
    }

    override fun saveToSpd(spd: Spd): Boolean {
        if (tvQjsy.trimText().isEmpty()) {
            ToastUtils.showShort("请假事由不能为空")
            return false
        }
        if (tvXjzl.trimText().isEmpty()) {
            ToastUtils.showShort("休假种类及年度不能为空")
            return false
        }
        if (tvKsrq.trimText().isEmpty()) {
            ToastUtils.showShort("开始日期不能为空")
            return false
        }
        if (tvJsrq.trimText().isEmpty()) {
            ToastUtils.showShort("结束日期不能为空")
            return false
        }

        spd.spdXx.apply {
            bt = tvBt.trimText()
            column2 = tvSqrq.trimText()
            column3 = tvKsrq.trimText()
            column4 = tvJsrq.trimText()
        }
        pairs.forEach { pair ->
            pair.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}