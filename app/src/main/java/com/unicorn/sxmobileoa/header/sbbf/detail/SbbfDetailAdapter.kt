package com.unicorn.sxmobileoa.header.sbbf.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.Key.nodeId
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.header.sbbf.model.Sbbf
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_equipment.*
import kotlinx.android.synthetic.main.item_wply.*

class SbbfDetailAdapter(val isCreate: Boolean) : BaseQuickAdapter<Sbbf, MyHolder>(R.layout.item_equipment) {

    val flag = Global.spd.nodeModel_1!!.nodeid in listOf("OA_FLOW_XZZB_SBBF_XXZSJD", "OA_FLOW_XZZB_SBBF_BGS", "OA_FLOW_XZZB_SBBF_BGSSH")
    val flag2 = Global.spd.nodeModel_1!!.nodeid == "OA_FLOW_XZZB_SBBF_GDZCGLKZX"

    override fun bindToRecyclerView(recyclerView: RecyclerView) {
        super.bindToRecyclerView(recyclerView)
        RxBus.get().registerEvent(CodeResult::class.java, recyclerView.context as LifecycleOwner, Consumer {
            val key = it.key
            Global.spd.set(key, it.result.text)
            val pos = key.substring(5, 6).toInt()
            notifyItemChanged(pos)
        })
    }


    override fun convert(helper: MyHolder, item: Sbbf) {
        helper.apply {
            tvBfsbmc.setText(item.bfsbmc)
            tvPpjxh.setText(item.ppjxh)
            tvSl.setText(item.sl)
            tvBfrq.text = item.bfrq
            tvBfjg.text = item.bfjg_select
            tvSfzx.text = item.sfzx_select

            tvBfsbmc.isEnabled = isCreate
            tvPpjxh.isEnabled = isCreate
            tvSl.isEnabled = isCreate
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            if (flag && adapterPosition != -1) {
                tvBfrq.clickDate(false)
                tvBfjg.clickCode("报废结果", "XZZB_SBBF_BFJG", mData[adapterPosition].key_bfjg_select)
            }
            if (flag2 && adapterPosition != -1){
                tvSfzx.clickCode("是否注销", "XZZB_SBBF_SFZX",  mData[adapterPosition].key_sfzx_select)
            }
            tvBfsbmc.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].bfsbmc = it
            }
            tvPpjxh.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].ppjxh = it
            }
            tvSl.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].sl = it
            }
            tvBfrq.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].bfrq = it
            }
        }
    }

}
