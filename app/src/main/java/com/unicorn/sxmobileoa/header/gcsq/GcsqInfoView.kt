package com.unicorn.sxmobileoa.header.gcsq

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
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
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
        renderView(menu, spd, isCreate)
        canEdit(spd, isCreate)
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
    private fun renderView(menu: Menu, spd: Spd, isCreate: Boolean) {
        // 新增时有些值需要赋值
        if (isCreate) {
            spd.spdXx.apply {
                spd.set(Key.sqr_input, createUserName)
                spd.set(Key.szbm_input, bmmc)
            }
        }

        // 展示值
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.apply {
                tvBt.setText(bt)
            tvKsrq.text = column3
            tvJsrq.text = column4
        }
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd, isCreate: Boolean) {
        if (isCreate) {
            tvBt.isEnabled = true
            tvWcr.clickDeptUser(Key.textResult, Key.mcwcr_input)
            tvFzbry.isEnabled = true
            tvWcdd.isEnabled = true
            tvWcsy.isEnabled = true
            tvKsrq.clickDate()
            tvJsrq.clickDate()
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer {
                tvWcr.text = it.result
            })
        }

        val nodeId = spd.nodeModel_1!!.nodeid
        tvBz.isEnabled = nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")
    }

    override fun saveToSpd(spd: Spd): Boolean {
        if (tvBt.trimText().isEmpty()) {
            ToastUtils.showShort("标题不能为空")
            return false
        }
        if (tvWcr.trimText().isEmpty()) {
            ToastUtils.showShort("外出人不能为空")
            return false
        }
        if (tvWcdd.trimText().isEmpty()) {
            ToastUtils.showShort("外出地点不能为空")
            return false
        }
        if (tvWcsy.trimText().isEmpty()) {
            ToastUtils.showShort("外出是由不能为空")
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
            column3 = tvKsrq.trimText()
            column4 = tvJsrq.trimText()
        }

        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}