package com.unicorn.sxmobileoa.sequenceFlow.network.nextUser

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spd.model.Spd

class NextUserRequest(spd: Spd, flow: NextTaskSequenceFlow) : MaybeRequest("nextUser") {

    init {
        addParameter("isCjblr", flow.isCjblr)
        addParameter("lastCourt", flow.lastCourt)
        addParameter("nextTaskKey", flow.nextTaskKey)
        addParameter(Key.processInstanceId, spd.spdXx.processInstancesId)
        addParameter(Key.spdId, spd.spdXx.id)

        val rolesId = when (flow.dealPerson) {
            "3" -> flow.dealPersonRoles
            "2" -> ""
            else -> ""
        }
        addParameter("rolesId", rolesId)

        val userIds = when (flow.dealPerson) {
            "1" -> ""
            "2" -> when (flow.dealPersonType) {
                "1" -> flow.startPersonId
                "2" -> Global.loginInfo!!.userId
                "3" -> flow.dealPersonId
                else -> ""
            }
            "3" -> ""
            else -> ""
        }
        addParameter("userIds", userIds)

        val orgCodes = if (flow.dealPerson == "3")
            when (flow.dealPersonRolesWayDep) {
                "1" -> Global.loginInfo!!.deptId
                "2" -> flow.dealPersonRolesWayDep
                "3" -> ""
                else -> Global.loginInfo!!.deptId   // ...
            }
        else ""
        addParameter("orgCodes", orgCodes)

        addParameter("lowerCode", "")
    }

}