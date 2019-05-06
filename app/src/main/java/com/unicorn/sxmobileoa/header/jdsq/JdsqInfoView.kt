package com.unicorn.sxmobileoa.header.jdsq

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
import com.unicorn.sxmobileoa.app.Key.key
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_jdsq.view.*
import org.joda.time.DateTime

@SuppressLint("ViewConstructor")
class JdsqInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_jdsq, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd, isCreate)
        canEdit(spd, isCreate)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrq, Key.sqrq_input))
            add(PAIR(tvCbbm, Key.cbbmmc_input))
//            add(PAIR(tvJcdd, Key.jcdd_select))
//            add(PAIR(tvCbr, Key.cbr_input))
            add(PAIR(tvJdrq, Key.jdrq_input))
            add(PAIR(tvLfkrdw, Key.lfkrdw_input))
            add(PAIR(tvXm, Key.xm_input))
            add(PAIR(tvLfrs, Key.lfrs_input))
            add(PAIR(tvJb, Key.jb_select))
            add(PAIR(tvLx, Key.lx_select))
            add(PAIR(tvPtry, Key.ptry_input))
            add(PAIR(tvLfsy, Key.lfsy_textarea))
            add(PAIR(tvJdje, Key.jdje_input))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd, isCreate: Boolean) {
        // 新增时有些值需要赋值
        if (isCreate) {
            spd.spdXx.apply {
                spd.set(Key.sqr_input, createUserName)
                spd.set(Key.sqrq_input, DateTime().toString("yyyy-MM-dd"))
                spd.set(Key.cbbmmc_input, bmmc)
                spd.set(Key.jdrq_input, DateTime().toString("yyyy-MM-dd"))
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
            tvCbbm.clickDept(Key.cbbmmc_input)
            tvJdrq.clickDate(false)
            tvLfkrdw.isEnabled = true
            tvXm.isEnabled = true
            tvLfrs.isEnabled = true
            tvJb.clickCode("级别", "JDGL_JDJLSQ_JB", Key.jb_input)
            tvPtry.isEnabled = true
            tvLfsy.isEnabled = true
            tvJdje.isEnabled = true
            tvLx.clickCode("类型", "JDGL_JDSQ_LX", Key.lx_input)
            tvBz.isEnabled = true
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer {
                when (it.key) {
                    Key.cbbmmc_input -> tvCbbm.text = it.result
                    Key.jb_input -> {
                        tvJb.text = it.result
                        spd.set(Key.jb_select, it.result)
                        spd.set(Key.jb_input, it.result)
                    }
                    Key.lx_input -> {
                        tvLx.text = it.result
                        spd.set(Key.lx_select, it.result)
                        spd.set(Key.lx_input, it.result)
                    }
                }
            })


        }
    }

    override fun saveToSpd(spd: Spd): Boolean {
        if (tvLfkrdw.trimText().isEmpty()) {
            ToastUtils.showShort("来访客人单位不能为空")
            return false
        }
        if (tvXm.trimText().isEmpty()) {
            ToastUtils.showShort("姓名不能为空")
            return false
        }
        if (tvLfrs.trimText().isEmpty()) {
            ToastUtils.showShort("来访人数不能为空")
            return false
        }
        if (tvJb.trimText().isEmpty()) {
            ToastUtils.showShort("级别不能为空")
            return false
        }
        if (tvPtry.trimText().isEmpty()) {
            ToastUtils.showShort("陪同人员不能为空")
            return false
        }
        if (tvLfsy.trimText().isEmpty()) {
            ToastUtils.showShort("来访事由、时间及行程安排计划不能为空")
            return false
        }
        if (tvJdje.trimText().isEmpty()) {
            ToastUtils.showShort("接待费用预算不能为空")
            return false
        }
        if (tvLx.trimText().isEmpty()) {
            ToastUtils.showShort("类型不能为空")
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