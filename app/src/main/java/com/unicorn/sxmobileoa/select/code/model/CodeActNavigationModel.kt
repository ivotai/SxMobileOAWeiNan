package com.unicorn.sxmobileoa.select.code.model

import dart.BindExtra
import dart.DartModel

@DartModel
class CodeActNavigationModel {

    @BindExtra
    lateinit var title: String

    @BindExtra
    lateinit var code: String

    @BindExtra
    lateinit var key: String

}