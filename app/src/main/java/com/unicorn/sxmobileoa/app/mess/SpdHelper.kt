package com.unicorn.sxmobileoa.app.mess

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.commitTask.model.BuinessCallBackParams
import com.unicorn.sxmobileoa.commitTask.model.NextTaskPersonModel
import com.unicorn.sxmobileoa.commitTask.model.TaskInstance
import com.unicorn.sxmobileoa.commitTask.model.TaskPerson
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.FlowNode
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.Spyj
import org.joda.time.DateTime
import java.util.*

class SpdHelper {

    private lateinit var currentFlowNodeList: List<FlowNode>

    fun addSpyjIfNeed(spd: Spd) {
        spd.apply {
            currentFlowNodeList =
                    if (nodeModel_1 != null) flowNodeList.filter { it.nodeid == nodeModel_1.nodeid }
                    else flowNodeList.filter { it.spyjNodeId == nodeModel_2!!.spyjNodeId }
        }

        // 归档，不做任何处理
        if (currentFlowNodeList.isEmpty()) return

        // 当前流程节点
        val currentFlowNode = currentFlowNodeList[0]
        // 当前可编辑审批意见
        val currentSpyjList = currentFlowNode.spyjList.filter { spyj ->
            spyj.spyjStatus == 0 && spyj.createUserId == Global.loginInfo!!.userId
        }
        // 如1果为空，则创建审批意见
        if (currentSpyjList.isEmpty()) {
            val spyj = Spyj(
                    createUserId = Global.loginInfo!!.userId,
                    createUserName = Global.loginInfo!!.userName,
                    flowCode = spd.spdXx.flowCode,
                    spyj = "",
                    spyjId = "",
                    spyjNodeId = currentFlowNode.safeSpyjNodeId,
                    spyjNodeName = currentFlowNode.safeSpyjNodeName!!,
                    spyjSort = currentFlowNode.spyjSort,
                    spyjSprId = Global.loginInfo!!.userId,
                    spyjSprName = Global.loginInfo!!.userName,
                    spyjStatus = 0,
                    spyjYwid = spd.spdXx.id,
                    sysTime = DateTime().toString("yyyy-MM-dd HH:mm:ss")
            )
            currentFlowNode.spyjList.add(spyj)
        }
    }

    fun canEdit2(nodeId: String): Boolean {
        val list = listOf("_SQR", "_NGR", "_QC", "_YBGS", "_LYR", "_TXJDSQ", "_BGSWS", "_NGRB", "_NBYJ", "_SFZBCSP", "_CBQK", "_SWDJ", "_NGYJ")
        return list.any { nodeId.contains(it) }
    }

    fun buildTaskInstance(menu: Menu, spd: Spd, response: SaveSpdResponse, sequenceFlow: NextTaskSequenceFlow, userList: List<User>): TaskInstance {
        val nodeId = spd.spdXx.nodeId
        val taskDefKey = sequenceFlow.nextTaskKey
        var gd = -1
        if (nodeId.contains("_GD") && menu.isGd == 1) {
            gd = 1
        }
//        val gd = if (nodeId.contains("_GD")) (if (menu.isGd == 1) 1 else -1) else 0
        return TaskInstance(
                processInstanceId = response.processInstancesId,
                taskId = response.taskId,
                taskPerson = TaskPerson(
                        personCode = Global.loginInfo!!.userId,
                        personName = Global.loginInfo!!.userName
                ),
                nextTaskPersonModel = ArrayList<NextTaskPersonModel>().apply {
                    NextTaskPersonModel(
                            taskType = sequenceFlow.nextTaskType,
                            taskDefKey = sequenceFlow.nextTaskKey,
                            approveType = sequenceFlow.approveType,
                            nextPersonCode = userList.joinToString(",") { it.id },
                            nextPersonName = userList.joinToString(",") { it.fullname },
                            nextLine = sequenceFlow.nextLine
                    ).let { this.add(it) }
                },
                approved = true,
                catagoryCode = response.flowCode,
                approveConent = "审判意见",
                spdId = response.primaryId,
                fydm = Global.court!!.dm,
                moduleCode = response.moduleCode,
                flowCode = response.flowCode,
                nodeId = response.nodeId,
                spyjId = response.spyjId,
                gd = gd,
                nextTaskKey = sequenceFlow.nextTaskKey,
                tasktype = sequenceFlow.nextTaskType,
                spdCode = response.spdCode,
                endTaskType = sequenceFlow.endTaskType,
                reject = "",
                sfjsjd = taskDefKey.toLowerCase() == "end" || taskDefKey == "结束",
                tzstate = false,
                buinessCallBackParams = BuinessCallBackParams("")
        )
    }

}