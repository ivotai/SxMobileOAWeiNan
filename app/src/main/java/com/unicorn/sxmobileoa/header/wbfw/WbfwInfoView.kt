package com.unicorn.sxmobileoa.header.wbfw

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_wbfw.view.*
import java.util.*

@SuppressLint("ViewConstructor")
class WbfwInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_wbfw, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvNgr, Key.ngr_input))
            add(PAIR(tvNgdw, Key.ngdw_input))
            add(PAIR(tvMj, Key.mjcd_select))
            add(PAIR(tvJdr, Key.jdr_input))
            add(PAIR(tvYssl, Key.yssl_input))
            add(PAIR(tvHj, Key.hjcd_select))
            add(PAIR(tvYsdw, Key.ysdw_input))
            add(PAIR(tvYssj, Key.yssj_input))
            add(PAIR(tvFwsj, Key.fwsj_input))
            add(PAIR(tvZsjg, Key.zsjgmc_input))
            add(PAIR(tvCsjg, Key.csjgmc_input))
            add(PAIR(tvFsjg, Key.fsjgmc_input))
            add(PAIR(tvYdfw, Key.ydfwmc_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.setText(spd.spdXx.bt)
        tvFwzh.text = spd.spdXx.sdwh    // 发文字号
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd) {
//        if (!SpdHelper().canEdit2(spd.nodeModel_2.nodeid)) return

        // 标题的编辑无法统一处理
        tvBt.apply {
            isEnabled = true
            textChanges2().subscribe { spd.spdXx.bt = it }
        }
        // 遍历，使其可编辑
        pairs.forEach {
            it.apply {
                textView.isEnabled = true
            }
        }


        // DATE
        tvYssj.safeClicks().subscribe {
            val now = Calendar.getInstance()
            val activity = context as BaseAct
            val dpd = DatePickerDialog.newInstance(
                    { _, year, monthOfYear, dayOfMonth ->
                        val month = monthOfYear + 1
                        val monthStr = if (month > 9) "$month" else "0$month"
                        val dayStr = if (dayOfMonth > 9) "$dayOfMonth" else "0$dayOfMonth"
                        val str = "$year-$monthStr-$dayStr"
                        tvYssj.text = str
                    },
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            )
            dpd.show(activity.fragmentManager, "dpd")
        }
        tvFwsj.safeClicks().subscribe {
            val now = Calendar.getInstance()
            val activity = context as BaseAct
            val dpd = DatePickerDialog.newInstance(
                    { _, year, monthOfYear, dayOfMonth ->
                        val month = monthOfYear + 1
                        val monthStr = if (month > 9) "$month" else "0$month"
                        val dayStr = if (dayOfMonth > 9) "$dayOfMonth" else "0$dayOfMonth"
                        val str = "$year-$monthStr-$dayStr"
                        tvFwsj.text = str
                    },
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            )
            dpd.show(activity.fragmentManager, "dpd")
        }

        // CODE & DEPT & DEPT_USER
        tvMj.clickCode("密级", "SPD_MJCD", Key.mjcd_select)
        tvHj.clickCode("缓急", "SPD_HJCD", Key.hjcd_select)
        tvZsjg.clickDept(Key.zsjgmc_input)
        tvCsjg.clickDept(Key.csjgmc_input)
        tvFsjg.clickDept(Key.fsjgmc_input)
        tvYdfw.clickDeptUser(Key.textResult, Key.ydfwmc_input)
        RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->
            when (textResult.key) {
                Key.mjcd_select -> tvMj
                Key.hjcd_select -> tvHj
                Key.zsjgmc_input -> tvZsjg
                Key.csjgmc_input -> tvCsjg
                Key.fsjgmc_input -> tvFsjg
                else -> tvYdfw
            }.text = textResult.result
        })
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