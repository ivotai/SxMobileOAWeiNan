package com.unicorn.sxmobileoa.spd.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.DialogUitls
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.commitTask.ui.CommitTaskAct
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.n.add.network.AddSpd
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.SpdActNavigationModel
import com.unicorn.sxmobileoa.spd.network.saveSpd.SaveSpd
import com.unicorn.sxmobileoa.spd.network.toSpd.ToSpd
import com.unicorn.sxmobileoa.spd.ui.headerView.ButtonFooterView
import com.unicorn.sxmobileoa.spd.ui.headerView.OperationHeaderView
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.title_recycler.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

@SuppressLint("CheckResult")
abstract class SpdAct : BaseAct() {

    var isCreate = false

    override fun registerEvent() {
        RxBus.get().registerEvent(CommitTaskSuccess::class.java, this, Consumer {
            finish()
        })
    }

    abstract fun addBasicHeaderView(): BasicInfoView

    override val layoutId = R.layout.title_recycler

    @DartModel
    lateinit var model: SpdActNavigationModel
    lateinit var spd: Spd
    private lateinit var basicInfoView: BasicInfoView

    override fun initViews() {
        titleBar.setTitle(model.menu.text)
        initRecyclerView()
    }

    val flowNodeAdapter = FlowNodeAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdAct)
            flowNodeAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        isCreate = intent.getBooleanExtra(Key.isCreate, false)
        if (isCreate) {
            AddSpd(model.menu.spdCode).toMaybe(this).subscribe {
                spd = it

                spd.flowNodeList.forEach { flowNode ->
                    flowNode.spyjList.forEach { item ->
                        val flag1 = if (flowNode.flowNodeId != null) flowNode.flowNodeId.contains(spd.spdXx.nodeId)
                        else item.spyjNodeId == spd.spdXx.nodeId
                        item.isCurrentNode = flag1 && item.spyjStatus == 0 && item.createUserId == Global.loginInfo!!.userId
                    }
                }

                // 供 equipmentAct 使用。
                Global.spd = spd
                spd.spdXx.apply {
                    moduleCode = model.menu.moduleCode
                    spdCode = model.menu.spdCode
                    flowCode = model.menu.flowCode
                }

                //
                basicInfoView = addBasicHeaderView()
                addFooterView()
            }
            return
        }
        // 非创建
        ToSpd(model.menu, model.param, model.type).toMaybe(this).subscribe {
            spd = it

            spd.flowNodeList.forEach { flowNode ->
                flowNode.spyjList.forEach { item ->
                    val flag1 = if (flowNode.flowNodeId != null) flowNode.flowNodeId.contains(spd.spdXx.nodeId)
                    else item.spyjNodeId == spd.spdXx.nodeId
                    item.isCurrentNode = flag1 && item.spyjStatus == 0 && item.createUserId == Global.loginInfo!!.userId
                }
            }

            // 供 equipmentAct 使用。
            Global.spd = spd
            // 添加 taskId
            spd.spdXx.taskId = model.param.taskId

//            处理审批意见
            if (model.type == "1") SpdHelper().addSpyjIfNeed(spd)

            flowNodeAdapter.setNewData(spd.flowNodeList)

            //
            addOperationHeaderView()
            basicInfoView = addBasicHeaderView()
            if (model.type == "1") addFooterView()
        }
    }

    private fun addOperationHeaderView() {
        OperationHeaderView(this, spd = spd).apply {
            flowNodeAdapter.addHeaderView(this)
        }
    }

    private fun addFooterView() {
        val footer = ButtonFooterView(this)
        flowNodeAdapter.addFooterView(footer)
//        footer.btnSave.safeClicks().subscribe { _ ->
//            // TODO QUESTION! 展开会向 flowNodeList 里添 sub
//            // TODO 不采用 textChange 方式 时刻保存到 spd 而是最后再保存
//            if (!basicInfoView.saveToSpd(spd)) return@subscribe
//            SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
//                model.param = Param(nodeId = it.nodeId, primaryId = it.primaryId, taskId = it.taskId)
//
//                if (!isCreate)
//                    ToastUtils.showShort("保存成功")
//                else {
//                    ToSpd(model.menu, model.param).toMaybe(this).subscribe {
//                        spd = it
//                        Global.spd = spd
//                        spd.spdXx.taskId = model.param.taskId
//                        SpdHelper().addSpyjIfNeed(spd)
//                        flowNodeAdapter.setNewData(spd.flowNodeList)
//                        ToastUtils.showShort("保存成功")
//                    }
//                }
//            }
//        }
        footer.btnNextStep.safeClicks().subscribe { _ ->
            if (!basicInfoView.saveToSpd(spd)) return@subscribe
            val mask = DialogUitls.showMask(this, "提交数据中...")

            // 第一次保存，产生 spd
            SaveSpd(spd).toMaybe(this@SpdAct).subscribe { saveSpdResponse ->
                model.param = Param(nodeId = saveSpdResponse.nodeId, primaryId = saveSpdResponse.primaryId, taskId = saveSpdResponse.taskId)
                if (!isCreate) {
                    mask.dismiss()
                    startCommitTaskAct(saveSpdResponse)
                } else {
                    // 第二次 重新加载页面
                    ToSpd(model.menu, model.param, model.type).toMaybe(this).subscribe {
                        spd = it
                        Global.spd = spd
                        spd.spdXx.taskId = model.param.taskId
                        SpdHelper().addSpyjIfNeed(spd)
                        flowNodeAdapter.setNewData(spd.flowNodeList)

                        // 第三次 再保存
                        SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
                            mask.dismiss()
                            model.param = Param(nodeId = saveSpdResponse.nodeId, primaryId = saveSpdResponse.primaryId, taskId = saveSpdResponse.taskId)
                            startCommitTaskAct(it)
                        }
                    }
                }
            }
        }
    }

    private fun startCommitTaskAct(saveSpdResponse: SaveSpdResponse) {
        startActivity(Intent(this@SpdAct, CommitTaskAct::class.java).apply {
            putExtra(Key.menu, model.menu)
            putExtra(Key.param, model.param)
            putExtra(Key.spd, spd)
            putExtra(Key.saveSpdResponse, saveSpdResponse)
        })
    }

}
