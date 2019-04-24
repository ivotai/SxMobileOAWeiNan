package com.unicorn.sxmobileoa.n.add

import android.annotation.SuppressLint
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.add.network.AddSpd
import kotlinx.android.synthetic.main.act_qjsq_add.*

class QjsqAddAct : BaseAct() {

    override val layoutId = R.layout.act_qjsq_add

    override fun initViews() {
        titleBar.setTitle("请假申请")
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        AddSpd("OA_SPD_QJGL_QJSQ").toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

}
