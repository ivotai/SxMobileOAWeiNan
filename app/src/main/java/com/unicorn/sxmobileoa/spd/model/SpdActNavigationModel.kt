package com.unicorn.sxmobileoa.spd.model

import android.support.annotation.Nullable
import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.simple.main.model.Menu
import dart.BindExtra
import dart.DartModel

@DartModel
class SpdActNavigationModel {

    @BindExtra
    lateinit var type: String

    @BindExtra
    lateinit var menu: Menu

    @Nullable
    @BindExtra
    lateinit var param: Param

}