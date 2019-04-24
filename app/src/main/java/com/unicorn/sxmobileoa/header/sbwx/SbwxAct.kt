package com.unicorn.sxmobileoa.header.sbwx

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class SbwxAct : SpdAct() {

    override fun addBasicHeaderView() = SbwxInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}