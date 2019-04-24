package com.unicorn.sxmobileoa.header.jdsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class JdsqAct : SpdAct() {

    override fun addBasicHeaderView() = JdsqInfoView(this, model.menu, spd,isCreate).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}