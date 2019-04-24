package com.unicorn.sxmobileoa.select.deptUser.model

import android.support.annotation.Nullable
import dart.BindExtra
import dart.DartModel

@DartModel
class DeptUserActNavigationModel {

    @BindExtra
    lateinit var type: String

    @Nullable
    @BindExtra
    lateinit var key: String

    @BindExtra
    lateinit var single:String

}