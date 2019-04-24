package com.unicorn.sxmobileoa.sequenceFlow.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.deptUser.model.DeptUserResult
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowActNavigationModel
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowResult
import com.unicorn.sxmobileoa.sequenceFlow.network.nextUser.NextUser
import com.unicorn.sxmobileoa.sequenceFlow.network.spdNext.SpdNext
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd_next.*

@SuppressLint("CheckResult")
class SequenceFlowAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择流程节点及人员")
        initRecyclerViews()
    }

    private val flowAdapter = NextTaskSequenceFlowAdapter()

    private val userAdapter = UserAdapter()

    private fun initRecyclerViews() {
        rvFlow.apply {
            layoutManager = LinearLayoutManager(this@SequenceFlowAct)
            flowAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        rvUser.apply {
            layoutManager = LinearLayoutManager(this@SequenceFlowAct)
            userAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getFlow()
        setOperation()
    }

    private fun getFlow() {
        SpdNext(model.param, model.spd).toMaybe(this)
                .map { it.nextTask_sequenceFlow }
                .subscribe {
                    flowAdapter.setNewData(it)
                }
    }

    private fun setOperation() {
        titleBar.setOperation("确认").safeClicks().subscribe { _ ->
            val selectedFlows = flowAdapter.data.filter { it.isSelected }
            if (selectedFlows.isEmpty()) {
                ToastUtils.showShort("请选择流程节点")
                return@subscribe
            }

            val selectedFlow = selectedFlows[0]
            if (selectedFlow.nextTaskShowName in listOf("结束","不通过结束")) {
                RxBus.get().post(SequenceFlowResult(selectedFlow, ArrayList()))
                finish()
                return@subscribe
            }

            val selectedUsers = userAdapter.data.filter { it.isSelected }
            if (selectedUsers.isEmpty()) {
                ToastUtils.showShort("请选择人员")
                return@subscribe
            }

            RxBus.get().post(SequenceFlowResult(selectedFlow, selectedUsers))
            finish()
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(NextTaskSequenceFlow::class.java, this, Consumer {
            if (it.nextTaskShowName in listOf("结束", "不通过结束")) {
                return@Consumer
            }
            val single = it.nextTaskType == "userTask"
            userAdapter.single = single
            NextUser(model.spd, it).toMaybe(this@SequenceFlowAct).subscribe { list ->
                list.apply { add(User("-1", "选择其他人员", "-1")) }
                        .let { listE -> userAdapter.setNewData(listE) }
            }
        })
        RxBus.get().registerEvent(DeptUserResult::class.java, this, Consumer { deptUserResult ->
            val selectedFlows = flowAdapter.data.filter { it.isSelected }
            RxBus.get().post(SequenceFlowResult(selectedFlows[0], deptUserResult.userList))
            finish()
        })
    }

    @DartModel
    lateinit var model: SequenceFlowActNavigationModel

    override val layoutId = R.layout.act_spd_next

}
