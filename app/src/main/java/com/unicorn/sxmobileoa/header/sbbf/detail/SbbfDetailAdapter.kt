package com.unicorn.sxmobileoa.header.sbbf.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.header.sbbf.model.Sbbf
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_equipment.*

class SbbfDetailAdapter : BaseQuickAdapter<Sbbf, MyHolder>(R.layout.item_equipment) {

    override fun convert(helper: MyHolder, item: Sbbf) {
        helper.apply {
            tvBfsbmc.text = item.bfsbmc
            tvPpjxh.text = item.ppjxh
            tvSl.text = item.sl
            tvBfrq.text = item.bfrq
            tvBfjg.text = item.bfjg_select
            tvSfzx.text = item.sfzx_select

            // canEdit
            val spd = Global.spd
            val nodeId = spd.nodeModel_1!!.nodeid
            val flag = nodeId in listOf("OA_FLOW_XZZB_SBBF_XXZSJD", "OA_FLOW_XZZB_SBBF_BGS", "OA_FLOW_XZZB_SBBF_BGSSH")
            if (flag) {
                tvBfrq.clickDate(false)
                tvBfjg.clickCode("报废结果", "XZZB_SBBF_BFJG", item.key_bfjg_select)
            }
            val flag2 = nodeId == "OA_FLOW_XZZB_SBBF_GDZCGLKZX"
            if (flag2) tvSfzx.clickCode("是否注销", "XZZB_SBBF_SFZX", item.key_sfzx_select)

            // cope CodeResult
            RxBus.get().registerEvent(CodeResult::class.java, mContext as LifecycleOwner, Consumer {
                val key_select = it.key
                spd.set(key_select, it.result.text)
//                val key_input = key_select.replace("_select", "_input")
//                spd.set(key_input, it.result.text)
                notifyDataSetChanged()
            })
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val helper = super.onCreateDefViewHolder(parent, viewType)
        helper.tvBfrq.textChanges2().filter { it.isNotEmpty() }.subscribe {
            if (helper.adapterPosition == -1) return@subscribe
            mData[helper.adapterPosition].bfrq = it
        }
        return helper
    }

}
