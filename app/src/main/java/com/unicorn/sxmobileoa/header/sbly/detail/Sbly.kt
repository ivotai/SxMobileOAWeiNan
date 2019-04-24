package com.unicorn.sxmobileoa.header.sbly.detail

import android.support.annotation.IntRange
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.spd.model.Spd

class Sbly(val spd: Spd, @IntRange(from = 1, to = 5) val position: Int) {

    val key_wpmc = "wpmc${position}_input"
    val key_sl = "sl${position}_input"
    val key_gg = "gg${position}_input"
    val key_pp = "pp${position}_input"
    val key_bz = "bz${position}_input"

    var wpmc
        get() = spd.get(key_wpmc)
        set(value) {
            spd.set(key_wpmc, value)
        }
    var sl
        get() = spd.get(key_sl)
        set(value) {
            spd.set(key_sl, value)
        }
    var gg
        get() = spd.get(key_gg)
        set(value) {
            spd.set(key_gg, value)
        }
    var pp
        get() = spd.get(key_pp)
        set(value) {
            spd.set(key_pp, value)
        }
    var bz
        get() = spd.get(key_bz)
        set(value) {
            spd.set(key_bz, value)
        }

}