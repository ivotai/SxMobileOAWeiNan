package com.unicorn.sxmobileoa.header.swgl

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class SwglAct : SpdAct() {

    override fun addBasicHeaderView() = SwglnfoView(this, model.menu, spd, isCreate).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}