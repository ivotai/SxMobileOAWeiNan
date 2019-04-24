package com.unicorn.sxmobileoa.header.wply.detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.header.wply.model.Wply
import kotlinx.android.synthetic.main.item_wply.*

class WplyAdapter : BaseQuickAdapter<Wply, MyHolder>(R.layout.item_wply) {

    override fun convert(helper: MyHolder, item: Wply) {
        helper.apply {
            tvWpmc.text = item.wpmc
            tvGg.text = item.gg
            tvSqsl.text = item.sqsl
            tvSlsl.setText(item.slsl)
            tvZkff.setText(item.zkff)

            // 是否可以编辑
            val nodeId = Global.spd.nodeModel_1!!.nodeid
            val canEdit = nodeId in listOf("OA_FLOW_HQGL_WPLY_KG", "OA_FLOW_HQGL_WPLY_WZGLYBL", "OA_FLOW_HQGL_WPLY_CGFF", "OA_FLOW_HQGL_WPLY_BGSSP", "OA_FLOW_HQGL_WPLY_HQFWZXZWK")
            tvSlsl.isEnabled = canEdit
            tvZkff.isEnabled = canEdit
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val helper = super.onCreateDefViewHolder(parent, viewType)
        helper.tvSlsl.textChanges2().filter { it.isNotEmpty() }.subscribe {
            val adapterPos = helper.adapterPosition
            if (adapterPos == -1) return@subscribe
            val target = mData[adapterPos]
            target.slsl = it
        }
        helper.tvZkff.textChanges2().filter { it.isNotEmpty() }.subscribe {
            val adapterPos = helper.adapterPosition
            if (adapterPos == -1) return@subscribe
            val target = mData[adapterPos]
            target.zkff = it
        }
        return helper
    }

}


//            tvWpmc.clickCode("物品名称", "WPLY_WP", item.key_wpmc)
//RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
//    item.spd.set(it.key, it.result)
//    notifyDataSetChanged()
//})