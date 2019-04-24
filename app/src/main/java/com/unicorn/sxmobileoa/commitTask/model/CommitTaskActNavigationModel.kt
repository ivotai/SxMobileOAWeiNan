package com.unicorn.sxmobileoa.commitTask.model

import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd
import dart.BindExtra
import dart.DartModel

@DartModel
class CommitTaskActNavigationModel {

    @BindExtra
    lateinit var menu: Menu

    @BindExtra
    lateinit var param: Param

    @BindExtra
    lateinit var spd: Spd

    @BindExtra
    lateinit var saveSpdResponse:SaveSpdResponse

}