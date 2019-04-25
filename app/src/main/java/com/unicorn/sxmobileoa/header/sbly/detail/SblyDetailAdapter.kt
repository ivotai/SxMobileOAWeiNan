package com.unicorn.sxmobileoa.header.sbly.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.Key.isCreate
import com.unicorn.sxmobileoa.app.Key.nodeId
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_sbly.*
import kotlinx.android.synthetic.main.item_sbly.tvGg
import kotlinx.android.synthetic.main.item_sbly.tvWpmc
import kotlinx.android.synthetic.main.item_wply.*

class SblyDetailAdapter(val isCreate: Boolean) : BaseQuickAdapter<Sbly, MyHolder>(R.layout.item_sbly) {

    val canEdit = Global.spd.nodeModel_1!!.nodeid in listOf("OA_FLOW_XZZB_SBLY_XXZXQRCG", "OA_FLOW_XZZB_SBLY_BGSSH", "OA_FLOW_XZZB_SBLY_XXZXQDCGHW")

    override fun bindToRecyclerView(recyclerView: RecyclerView) {
        super.bindToRecyclerView(recyclerView)
        RxBus.get().registerEvent(CodeResult::class.java, recyclerView.context as LifecycleOwner, Consumer {
            val str = it.key.substring(0, it.key.indexOf("_"))
            val pos = str.toInt()
            if (pos == -1) return@Consumer
            val item = data[pos]
            item.wpmc = it.result.text
            item.gg = it.result.code
            notifyItemChanged(pos)
        })
    }

    override fun convert(helper: MyHolder, item: Sbly) {
        helper.apply {
            tvWpmc.text = item.wpmc
            tvSl.setText(item.sl)
            tvGg.setText(item.gg)
            tvPp.setText(item.pp)
            tvBz.setText(item.bz)

            tvSl.isEnabled = canEdit
            tvGg.isEnabled = canEdit
            tvPp.isEnabled = canEdit
            tvBz.isEnabled = canEdit
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            if (canEdit){
                tvWpmc.safeClicks().subscribe {
                    mContext.startActivity(Intent(mContext, CodeAct::class.java).apply {
                        putExtra(Key.title, "设备名称")
                        putExtra(Key.code, "SBLY_WP")
                        putExtra(Key.key, "${adapterPosition}_select")
                    })
                }
            }
            tvSl.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].sl = it
            }
            tvGg.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].gg = it
            }
            tvPp.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].pp = it
            }
            tvBz.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].bz = it
            }
        }
    }

}
