package com.unicorn.sxmobileoa.header.sbbf.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.Key.code
import com.unicorn.sxmobileoa.app.Key.key
import com.unicorn.sxmobileoa.app.Key.nodeId
import com.unicorn.sxmobileoa.app.Key.title
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.header.sbbf.model.Sbbf
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_equipment.*
import kotlinx.android.synthetic.main.item_wply.*

class SbbfDetailAdapter : BaseQuickAdapter<Sbbf, MyHolder>(R.layout.item_equipment) {

    private val nodeId = Global.spd.nodeModel_1!!.nodeid
    private val canEditForCreator = nodeId == "OA_FLOW_XZZB_SBBF_SQR"
    private val flag = nodeId in listOf("OA_FLOW_XZZB_SBBF_XXZSJD", "OA_FLOW_XZZB_SBBF_BGS", "OA_FLOW_XZZB_SBBF_BGSSH")
    private val flag2 = nodeId == "OA_FLOW_XZZB_SBBF_GDZCGLKZX"

    override fun bindToRecyclerView(recyclerView: RecyclerView) {
        super.bindToRecyclerView(recyclerView)
        RxBus.get().registerEvent(CodeResult::class.java, recyclerView.context as LifecycleOwner, Consumer {
            val str = it.key.substring(0, it.key.indexOf("_"))
            val pos = str.toInt()
            if (pos == -1) return@Consumer
            val item = data[pos]
            if (it.key.contains("bfjg")) {
                item.bfjg_select = it.result.text
            } else {
                item.sfzx_select = it.result.text
            }
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

            tvBfsbmc.isEnabled = canEditForCreator
            tvPpjxh.isEnabled = canEditForCreator
            tvSl.isEnabled = canEditForCreator
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            if (flag) {
                tvBfrq.clickDate(false)
                tvBfjg.safeClicks().subscribe {
                    if (adapterPosition == -1) return@subscribe
                    mContext.startActivity(Intent(mContext, CodeAct::class.java).apply {
                        putExtra(Key.title, "报废结果")
                        putExtra(Key.code, "XZZB_SBBF_BFJG")
                        putExtra(Key.key, "${adapterPosition}_bfjg_select")
                    })
                }
            }
            if (flag2) {
                tvSfzx.safeClicks().subscribe {
                    if (adapterPosition == -1) return@subscribe
                    mContext.startActivity(Intent(mContext, CodeAct::class.java).apply {
                        putExtra(Key.title, "是否注销")
                        putExtra(Key.code, "XZZB_SBBF_SFZX")
                        putExtra(Key.key, "${adapterPosition}_sfzx_select")
                    })
                }
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
