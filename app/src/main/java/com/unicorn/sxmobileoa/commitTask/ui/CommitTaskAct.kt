package com.unicorn.sxmobileoa.commitTask.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskActNavigationModel
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.commitTask.network.CommitTask
import com.unicorn.sxmobileoa.commitTask.network.checkGd.CheckGd
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowResult
import com.unicorn.sxmobileoa.sequenceFlow.ui.SequenceFlowAct
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_commit_task.*

@SuppressLint("CheckResult")
class CommitTaskAct : BaseAct() {

    private var sequenceFlowResult: SequenceFlowResult? = null

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
    }

    override fun bindIntent() {
        clicks()
        setOperation()
    }

    private fun clicks() {
        tvSequenceFlow.safeClicks().mergeWith(tvUsers.safeClicks()).subscribe {
            startActivity(Intent(this@CommitTaskAct, SequenceFlowAct::class.java).apply {
                putExtra(Key.param, model.param)
                putExtra(Key.spd, model.spd)
            })
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(SequenceFlowResult::class.java, this, Consumer { result ->
            sequenceFlowResult = result
            tvSequenceFlow.text = result.flow.nextTaskShowName
            tvUsers.text = result.userList.joinToString(",") { user -> user.fullname }
            if (tvSequenceFlow.text.toString() in listOf("结束", "不通过结束")) {
                tvUsers.text = "无需选择人员"
            }
        })
    }

    private fun setOperation() {
        titleBar.setOperation("确定").safeClicks().subscribe {
            checkGd()
        }
    }

    private fun checkGd() {
        val temp = sequenceFlowResult
        if (temp == null) {
            ToastUtils.showShort("请选择流程节点及人员")
            return
        }
        val nextTaskKey = temp.flow.nextTaskKey
        if (nextTaskKey.contains("_GD")) {
            CheckGd(model.spd.spdXx.processInstancesId, model.spd.spdXx.taskId)
                    .toMaybe(this)
                    .subscribe {
                        if (it.message != "1")
                            showConfirm()
                        else
                            commitTask()
                    }
        } else {
            commitTask()
        }
    }

    private fun showConfirm() {
        MaterialDialog.Builder(this)
                .title("当前流程存在未审批完成的待办任务，提交至归档阶段,其他待办任务将无法处理，是否继续？")
                .positiveText("确认")
                .negativeText("取消")
                .onPositive { _, _ ->
                    commitTask()
                }.show()
    }

    private fun commitTask() {
        val temp = sequenceFlowResult
        if (temp == null) {
            ToastUtils.showShort("请选择流程节点及人员")
            return
        }
        val taskInstance = SpdHelper().buildTaskInstance(model.menu,model.spd, model.saveSpdResponse, temp.flow, temp.userList)
        CommitTask(taskInstance).toMaybe(this).subscribe { _ ->
            ToastUtils.showShort("提交成功")
            RxBus.get().post(CommitTaskSuccess())
            finish()
        }
    }

    @DartModel
    lateinit var model: CommitTaskActNavigationModel

    override val layoutId = R.layout.act_commit_task

}