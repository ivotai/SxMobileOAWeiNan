package com.unicorn.sxmobileoa.header.sbbf

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class SbbfAct : SpdAct() {

    override fun addBasicHeaderView() = SbbfInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}