package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.header.gcsq.GcsqAct
import com.unicorn.sxmobileoa.header.jdsq.JdsqAct
import com.unicorn.sxmobileoa.header.nbfw.NbfwAct
import com.unicorn.sxmobileoa.header.qjsq.QjsqAct
import com.unicorn.sxmobileoa.header.sbbf.SbbfAct
import com.unicorn.sxmobileoa.header.sbly.SblyAct
import com.unicorn.sxmobileoa.header.sbwx.SbwxAct
import com.unicorn.sxmobileoa.header.swgl.SwglAct
import com.unicorn.sxmobileoa.header.wbfw.WbfwAct
import com.unicorn.sxmobileoa.header.wply.WplyAct
import com.unicorn.sxmobileoa.header.ycsq.YcsqAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu

class DbxxAdapter(private val menu: Menu, private val type: String) : BaseQuickAdapter<Dbxx, BaseViewHolder>(R.layout.item_dbxx) {

    override fun convert(helper: BaseViewHolder, item: Dbxx) {
        helper.apply {
            setText(R.id.tvBt, item.bt)
            setText(R.id.tvNodeName, item.nodeName)
            setText(R.id.tvWh, "文号：${item.wh}")
            setText(R.id.tvNgrName, "拟稿人：${item.ngrName}")
            setText(R.id.tvCjrq, "时间：${item.cjrq}")
            setText(R.id.tvNgrDept, "拟稿部门：${item.ngrDept}")

            getView<View>(R.id.root).safeClicks().subscribe { _ ->
                var cls: Class<*>? = null
                when (menu.text) {
                    "内部发文" -> cls = NbfwAct::class.java
                    "外部发文" -> cls = WbfwAct::class.java
                    "收文管理" -> cls = SwglAct::class.java
                    "设备维修" -> cls = SbwxAct::class.java
                    "设备报废" -> cls = SbbfAct::class.java
                    "请假申请" -> cls = QjsqAct::class.java
                    "公出申请" -> cls = GcsqAct::class.java
                    "接待申请" -> cls = JdsqAct::class.java
                    "物品领用" -> cls = WplyAct::class.java
                    "设备领用" -> cls = SblyAct::class.java
                    "用车申请" -> cls = YcsqAct::class.java
                }
                if (cls == null) return@subscribe

                mContext.startActivity(Intent(mContext, cls).apply {
                    putExtra(Key.menu, menu)
                    putExtra(Key.param, item.param)
                    putExtra(Key.type, this@DbxxAdapter.type)
                })
            }
        }
    }

}