package com.unicorn.sxmobileoa.sequenceFlow.model

import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.spd.model.Spd
import dart.BindExtra
import dart.DartModel

@DartModel
class SequenceFlowActNavigationModel {

    @BindExtra
    lateinit var param: Param

    @BindExtra
    lateinit var spd: Spd

}