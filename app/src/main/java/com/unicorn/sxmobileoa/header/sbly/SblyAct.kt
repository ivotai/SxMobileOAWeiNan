package com.unicorn.sxmobileoa.header.sbly

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class SblyAct : SpdAct() {

    override fun addBasicHeaderView() = SblyInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}