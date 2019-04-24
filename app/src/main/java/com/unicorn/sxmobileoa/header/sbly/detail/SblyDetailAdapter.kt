package com.unicorn.sxmobileoa.header.sbly.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.clickCode
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.app.textChanges2
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_sbly.*

class SblyDetailAdapter : BaseQuickAdapter<Sbly, MyHolder>(R.layout.item_sbly) {

    override fun convert(helper: MyHolder, item: Sbly) {
        helper.apply {
            tvWpmc.text = item.wpmc
            tvSl.setText(item.sl)
            tvGg.setText(item.gg)
            tvPp.setText(item.pp)
            tvBz.setText(item.bz)

            val nodeId = Global.spd.nodeModel_1!!.nodeid
            val canEdit = nodeId in listOf("OA_FLOW_XZZB_SBLY_XXZXQRCG", "OA_FLOW_XZZB_SBLY_BGSSH", "OA_FLOW_XZZB_SBLY_XXZXQDCGHW")
            if (canEdit) tvWpmc.clickCode("设备名称", "SBLY_WP", item.key_wpmc)
            RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
                item.spd.set(it.key,  it.result)
                notifyDataSetChanged()
            })
            tvSl.isEnabled = canEdit
            tvGg.isEnabled = canEdit
            tvPp.isEnabled = canEdit
            tvBz.isEnabled = canEdit
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val helper = super.onCreateDefViewHolder(parent, viewType)
        helper.tvSl.textChanges2().filter { it.isNotEmpty() }.subscribe {
            if (helper.adapterPosition == -1) return@subscribe
            mData[helper.adapterPosition].sl = it
        }
        helper.tvGg.textChanges2().filter { it.isNotEmpty() }.subscribe {
            if (helper.adapterPosition == -1) return@subscribe
            mData[helper.adapterPosition].gg = it
        }
        helper.tvPp.textChanges2().filter { it.isNotEmpty() }.subscribe {
            if (helper.adapterPosition == -1) return@subscribe
            mData[helper.adapterPosition].pp = it
        }
        helper.tvBz.textChanges2().filter { it.isNotEmpty() }.subscribe {
            if (helper.adapterPosition == -1) return@subscribe
            mData[helper.adapterPosition].bz = it
        }
        return helper
    }

}
