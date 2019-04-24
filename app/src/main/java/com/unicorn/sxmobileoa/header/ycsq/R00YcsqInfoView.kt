package com.unicorn.sxmobileoa.header.ycsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
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
import com.unicorn.sxmobileoa.header.ycsq.cllx.ui.CllxAct
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_ycsq_r00.view.*
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.format.DateTimeFormat


@SuppressLint("ViewConstructor")
class R00YcsqInfoView(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd, isCreate)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd, isCreate: Boolean) {

        LayoutInflater.from(context).inflate(R.layout.header_view_ycsq_r00, this, true)
        if (isCreate) divider.visibility = View.GONE
        preparePairs()
        renderView(menu, spd, isCreate)
        canEdit(spd, isCreate)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvHbmc, Key.hbmc_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrdh, Key.sqrdh_input))
            add(PAIR(tvCfsj, Key.cfsj_input))
            add(PAIR(tvFhsj, Key.fhsj_input))
            add(PAIR(tvCcrmc, Key.ccrmc_input))
            add(PAIR(tvYcrs, Key.ycrs_input))
            add(PAIR(tvCcsj1, Key.ccsjxm1_input))
            add(PAIR(tvCcsj2, Key.ccsjxm2_input))
            add(PAIR(tvCcsj3, Key.ccsjxm3_input))
            add(PAIR(tvSycl1, Key.syclcp1_input))
            add(PAIR(tvSycl2, Key.syclcp2_input))
            add(PAIR(tvSycl3, Key.syclcp3_input))
            add(PAIR(tvPcsj, Key.pcsj_input))
            add(PAIR(tvHzsj, Key.hzsj_date))
            add(PAIR(tvBcsm, Key.bcsm_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd, isCreate: Boolean) {
        // 新增时有些值需要赋值
        if (isCreate) {
            spd.spdXx.apply {
                spd.set(Key.sqr_input, createUserName)  // 申请人
                spd.set(Key.hbmc_input, bmmc)           // 用车部门
            }
        }

        //
        if (canEdit(spd)) {
            if (spd.spdXx.column8.isEmpty())
                spd.spdXx.column8 = Global.loginInfo!!.userName
            if (spd.get(Key.pcsj_input).isEmpty())
                spd.set(Key.pcsj_input, DateTime().toString("yyyy-MM-dd"))
        }

        // 展示值
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.apply {
            tvBt.setText(bt)
            tvYcsy.setText(column1)
            tvKwdd.setText(column3)
            tvPcr.text = column8
        }
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun canEdit(spd: Spd, isCreate: Boolean) {
        if (isCreate) {
            tvBt.isEnabled = true
            tvHbmc.clickDept(Key.hbmc_input)
            tvSqrdh.isEnabled = true
            tvCfsj.clickDate()
            tvFhsj.clickDate()
            tvCcrmc.clickDeptUser(Key.textResult, Key.ccrmc_input)
            tvYcsy.safeClicks().subscribe {
                context.startActivity(Intent(context, CllxAct::class.java).apply {
                    putExtra(Key.key, Key.cllx_input)
                    putExtra(Key.title,"选择用车事由")
                })
            }
            tvKwdd.isEnabled = true
            tvYcrs.isEnabled = true
        }

        if (canEdit(spd)) {
            tvCcsj1.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj1_select)
            tvCcsj2.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj2_select)
            tvCcsj3.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj3_select)
            tvSycl1.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl1_select)
            tvSycl2.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl2_select)
            tvSycl3.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl3_select)
            tvPcsj.clickDate(false)
            tvHzsj.clickDate(false)
        }
        RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer {
            when (it.key) {
                Key.hbmc_input -> tvHbmc
                Key.ccrmc_input -> tvCcrmc
                else -> tvYcsy
            }.text = it.result
        })
        RxBus.get().registerEvent(CodeResult::class.java, context as LifecycleOwner, Consumer {
            it.apply {
                spd.set(key, result.`val`)
                when (it.key) {
                    Key.ccsj1_select -> tvCcsj1
                    Key.ccsj2_select -> tvCcsj2
                    Key.ccsj3_select -> tvCcsj3
                    Key.sycl1_select -> tvSycl1
                    Key.sycl2_select -> tvSycl2
                    else -> tvSycl3
                }.text = result.text
            }
        })
    }

    private fun canEdit(spd: Spd): Boolean {
        val nodeId = spd.nodeModel_1!!.nodeid
        return nodeId in listOf(
                "OA_FLOW_HQGL_YCSQ_CGSP",
                "OA_FLOW_HQGL_YCSQ_CDSP",
                "OA_FLOW_HQGL_YCSQ_CDPC",
                "OA_FLOW_HQGL_YCSQ_CGK",
                "OA_FLOW_HQGL_YCSQ_BGSSP",
                "OA_FLOW_HQGL_YCSQ_CDDZ",
                "OA_FLOW_HQGL_YCSQ_TZ",
                "OA_FLOW_HQGL_YCSQ_FYZSP"
        )
    }


    override fun saveToSpd(spd: Spd): Boolean {
        if (tvYcsy.trimText().isEmpty()) {
            ToastUtils.showShort("用车事由不能为空")
            return false
        }
        if (tvSqrdh.trimText().isEmpty()) {
            ToastUtils.showShort("电话不能为空")
            return false
        }
        if (tvCfsj.trimText().isEmpty()) {
            ToastUtils.showShort("出发时间不能为空")
            return false
        }
        if (tvFhsj.trimText().isEmpty()) {
            ToastUtils.showShort("返回时间不能为空")
            return false
        }
        //  验证时间，计算天数
        val format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")
        val cfsj = DateTime.parse(tvCfsj.trimText(), format)
        val fhsj = DateTime.parse(tvFhsj.trimText(), format)
        if (cfsj.isAfter(fhsj)) {
            ToastUtils.showShort("出发时间不能晚于返回时间")
            return false
        }
        val ts = Days.daysBetween(cfsj, fhsj).days
        spd.set(Key.ts_input, "共${ts + 1}天")

        if (tvCcrmc.trimText().isEmpty()) {
            ToastUtils.showShort("乘车人不能为空")
            return false
        }
        if (tvKwdd.trimText().isEmpty()) {
            ToastUtils.showShort("开往地点不能为空")
            return false
        }
        if (tvYcrs.trimText().isEmpty()) {
            ToastUtils.showShort("用车人数不能为空")
            return false
        }

        spd.spdXx.apply {
            bt = tvBt.trimText()
            column1 = tvYcsy.trimText()
            column3 = tvKwdd.trimText()
        }

        // column4 司机总信息
        listOf(tvCcsj1, tvCcsj2, tvCcsj3)
                .map { it.trimText() }
                .map { it.split("--")[0] }
                .filter { it != "" }
                .joinToString(",") { it }
                .let {
                    spd.spdXx.column4 = it
                }

        // column5 车辆总信息
        listOf(tvSycl1, tvSycl2, tvSycl3)
                .map { it.trimText() }
                .filter { it != "" }
                .joinToString(",") { it }
                .let {
                    spd.spdXx.column5 = it
                }

        pairs.forEach { pair ->
            pair.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}