package com.unicorn.sxmobileoa.header.ycsq

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.spd.ui.SpdAct

class YcsqAct : SpdAct() {

    override fun addBasicHeaderView(): BasicInfoView {
        return if (Global.court!!.dm == "R00")
            R00YcsqInfoView(this, model.menu, spd, isCreate).apply {
                flowNodeAdapter.addHeaderView(this)
            }
        else
            YcsqInfoView(this, model.menu, spd, isCreate).apply {
                flowNodeAdapter.addHeaderView(this)
            }
    }

}