package com.unicorn.sxmobileoa.header.gcsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class GcsqAct : SpdAct() {

    override fun addBasicHeaderView() = GcsqInfoView(this, model.menu, spd, isCreate).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}