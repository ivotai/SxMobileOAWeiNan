package com.unicorn.sxmobileoa.header.wply.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.provider.Settings
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.Key.code
import com.unicorn.sxmobileoa.app.Key.key
import com.unicorn.sxmobileoa.app.Key.title
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.CodeResult
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.wply.model.Wply
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_wply.*
import java.nio.charset.CoderMalfunctionError
import java.nio.charset.CoderResult

class WplyAdapter : BaseQuickAdapter<Wply, MyHolder>(R.layout.item_wply) {

    private val nodeId = Global.spd.nodeModel_1!!.nodeid
    private val canEditForCreator = nodeId == "OA_FLOW_HQGL_WPLY_SQ"
    override fun bindToRecyclerView(recyclerView: RecyclerView) {
        super.bindToRecyclerView(recyclerView)
        RxBus.get().registerEvent(CodeResult::class.java, recyclerView.context as LifecycleOwner, Consumer {
            val str = it.key.substring(0, it.key.indexOf("_"))
            val pos = str.toInt()
            if (pos == -1) return@Consumer
            val item = data[pos]
            item.wpmc = it.result.text
            item.gg = it.result.`val`
            item.sqdw = it.result.code
            notifyItemChanged(pos)
        })
    }

    override fun convert(helper: MyHolder, item: Wply) {
        helper.apply {
            tvWpmc.text = item.wpmc
            tvGg.setText(item.gg)
            tvSqsl.setText(item.sqsl)
            tvSqdw.setText(item.sqdw)
            tvSlsl.setText(item.slsl)
            tvZkff.setText(item.zkff)

            tvGg.isEnabled = canEditForCreator
            tvSqsl.isEnabled = canEditForCreator
            tvSqdw.isEnabled = canEditForCreator

            // 是否可以编辑
            val canEdit = nodeId in listOf("OA_FLOW_HQGL_WPLY_KG", "OA_FLOW_HQGL_WPLY_WZGLYBL", "OA_FLOW_HQGL_WPLY_CGFF", "OA_FLOW_HQGL_WPLY_BGSSP", "OA_FLOW_HQGL_WPLY_HQFWZXZWK")
            tvSlsl.isEnabled = canEdit
            tvZkff.isEnabled = canEdit
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            if (canEditForCreator) {
                tvWpmc.safeClicks().subscribe {
                    mContext.startActivity(Intent(mContext, CodeAct::class.java).apply {
                        putExtra(Key.title, "物品名称")
                        putExtra(Key.code, "WPLY_WP")
                        putExtra(Key.key, "${adapterPosition}_select")
                    })
                }
                tvGg.textChanges2().subscribe {
                    if (adapterPosition == -1) return@subscribe
                    mData[adapterPosition].gg = it
                }
                tvSqsl.textChanges2().subscribe {
                    if (adapterPosition == -1) return@subscribe
                    mData[adapterPosition].sqsl = it
                }
                tvSqdw.textChanges2().subscribe {
                    if (adapterPosition == -1) return@subscribe
                    mData[adapterPosition].sqdw = it
                }
                // 神奇的闭包
//                tvWpmc.clickCode("物品名称", "WPLY_WP", "${adapterPosition}_select")
            }
            tvSlsl.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].slsl = it
            }
            tvZkff.textChanges2().subscribe {
                if (adapterPosition == -1) return@subscribe
                mData[adapterPosition].zkff = it
            }
        }
    }


}


//RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
//    item.spd.set(it.key, it.result)
//    notifyDataSetChanged()
//})