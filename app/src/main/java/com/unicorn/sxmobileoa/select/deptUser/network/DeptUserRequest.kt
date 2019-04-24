package com.unicorn.sxmobileoa.select.deptUser.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class DeptUserRequest(deptId: String) : MaybeRequest("userList") {

    init {
        addParameter("bmbm", deptId)
    }

}