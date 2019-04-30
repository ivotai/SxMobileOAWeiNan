package com.unicorn.sxmobileoa.header.jdsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.Key.isCreate
import com.unicorn.sxmobileoa.app.Key.nodeId
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
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
        canEdit(isCreate)
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
            }
        }

        // 展示值
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }
    private fun canEdit(isCreate: Boolean) {
        if (isCreate) {
            tvCbbm.clickDept(Key.cbbmmc_input)
            tvJdrq.clickDate(false)


                        //            tvZw.isEnabled = true
                        //            tvQjsy.isEnabled = true
                        //                    tvXjzl.clickCode("休假种类及年度", "QJSQ_JQ", Key.xjzljsy_select)
                        //                    tvKsrq.clickDate()
                        //                    tvJsrq.clickDate()
                        //            RxBus.get().registerEvent(CodeResult::class.java, context as LifecycleOwner, Consumer {
                        //                // somehow val equals text
                        //                tvXjzl.text = it.result.text
                        //            })
        }
      }

    override fun saveToSpd(spd: Spd): Boolean {
        return true
    }

}