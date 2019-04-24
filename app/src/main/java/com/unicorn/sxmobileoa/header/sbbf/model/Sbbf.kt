package com.unicorn.sxmobileoa.header.sbbf.model

import android.support.annotation.IntRange
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.spd.model.Spd

class Sbbf(val spd: Spd, @IntRange(from = 1, to = 5) val position: Int) {

    val key_bfsbmc = "bfsbmc${position}_input"
    val key_ppjxh = "ppjxh${position}_input"
    val key_sl = "sl${position}_input"
    val key_bfrq = "bfrq${position}_date"
//    val key_bfjg_input = "bfjg${position}_input"
    val key_bfjg_select = "bfjg${position}_select"
//    val key_sfzx_input = "sfzx${position}_input"
    val key_sfzx_select = "sfzx${position}_select"

    var bfsbmc
        get() = spd.get(key_bfsbmc)
        set(value) {
            spd.set(key_bfsbmc, value)
        }
    var ppjxh
        get() = spd.get(key_ppjxh)
        set(value) {
            spd.set(key_ppjxh, value)
        }
    var sl
        get() = spd.get(key_sl)
        set(value) {
            spd.set(key_sl, value)
        }
    var bfrq
        get() = spd.get(key_bfrq)
        set(value) {
            spd.set(key_bfrq, value)
        }
    var bfjg_select
        get() = spd.get(key_bfjg_select)
        set(value) {
            spd.set(key_bfjg_select, value)
        }
    var sfzx_select
        get() = spd.get(key_sfzx_select)
        set(value) {
            spd.set(key_sfzx_select, value)
        }

}