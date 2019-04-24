package com.unicorn.sxmobileoa.header

import com.unicorn.sxmobileoa.spd.model.Spd

interface BasicInfoView {

    fun saveToSpd(spd: Spd): Boolean

}