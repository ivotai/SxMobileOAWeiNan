package com.unicorn.sxmobileoa.header.nbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class NbfwAct : SpdAct() {

    override fun addBasicHeaderView() = NbfwInfoView(this, model.menu, spd, isCreate).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}