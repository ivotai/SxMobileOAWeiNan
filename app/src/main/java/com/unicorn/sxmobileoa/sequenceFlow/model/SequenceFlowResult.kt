package com.unicorn.sxmobileoa.sequenceFlow.model

import com.unicorn.sxmobileoa.select.deptUser.model.User

class SequenceFlowResult(val flow: NextTaskSequenceFlow, var userList: List<User>)
