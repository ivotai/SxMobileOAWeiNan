package com.unicorn.sxmobileoa.header.wply.model

import android.support.annotation.IntRange
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.spd.model.Spd

class Wply(val spd: Spd, @IntRange(from = 1, to = 5) val position: Int) {

    val key_wpmc = "wpmc${position}_input"
    val key_wpmc_select = "wpmc${position}_select"  // select 需要保存和 input 一样的值
    val key_gg = "gg${position}_input"
    val key_sqdw = "sqdw${position}_input"
    val key_sqsl = "sqsl${position}_input"
    val key_slsl = "slsl${position}_input"
    val key_zkff = "zkff${position}_input"

    var wpmc
        get() = spd.get(key_wpmc)
        set(value) {
            spd.set(key_wpmc, value)
            spd.set(key_wpmc_select, value)
        }
    var gg
        get() = spd.get(key_gg)
        set(value) {
            spd.set(key_gg, value)
        }
    var sqsl
        get() = spd.get(key_sqsl)
        set(value) {
            spd.set(key_sqsl, value)
        }
    var slsl
        get() = spd.get(key_slsl)
        set(value) {
            spd.set(key_slsl, value)
        }
    var zkff
        get() = spd.get(key_zkff)
        set(value) {
            spd.set(key_zkff, value)
        }
    var sqdw
        get() = spd.get(key_sqdw)
        set(value) {
            spd.set(key_sqdw, value)
        }

}