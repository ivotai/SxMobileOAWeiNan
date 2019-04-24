package com.unicorn.sxmobileoa.header.wply.model

import android.support.annotation.IntRange
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.spd.model.Spd

class Wply(val spd: Spd, @IntRange(from = 1, to = 5) val position: Int) {

    val key_wpmc = "wpmc${position}_input"
    val key_gg = "gg${position}_input"
    val key_sqsl = "sqsl${position}_input"
    val key_slsl = "slsl${position}_input"
    val key_zkff = "zkff${position}_input"

    var wpmc
        get() = spd.get(key_wpmc)
        set(value) {
            spd.set(key_wpmc, value)
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

}