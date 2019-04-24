package com.unicorn.sxmobileoa.spd.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.spd.model.FlowNode
import com.unicorn.sxmobileoa.spd.model.Spyj
import kotlinx.android.synthetic.main.item_spyj.*
import org.joda.time.DateTime

class FlowNodeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, MyHolder>(null) {

    companion object {
        const val TYPE_FLOW_NODE = 0
        const val TYPE_SPYJ = 1
    }

    init {
        addItemType(TYPE_FLOW_NODE, R.layout.item_flow_node)
        addItemType(TYPE_SPYJ, R.layout.item_spyj)
    }

    @SuppressLint("SetTextI18n")
    override fun convert(helper: MyHolder, item: MultiItemEntity) {
        when (item.itemType) {
            TYPE_FLOW_NODE -> {
                item as FlowNode
                helper.setText(R.id.tvSpyjNodeName, item.safeSpyjNodeName)

                helper.getView<View>(R.id.ivArrow).visibility =
                        if (item.spyjList.isEmpty()) View.INVISIBLE
                        else View.VISIBLE

                helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
                    if (item.subItems.isEmpty()) return@subscribe
                    if (item.isExpanded) collapse(helper.adapterPosition)
                    else expand(helper.adapterPosition)
                }
            }
            TYPE_SPYJ -> {
                item as Spyj
                helper.setText(R.id.tvSpyjSprName, item.spyjSprName)
                helper.setText(R.id.tvSysTime, item.sysTime)
                helper.setText(R.id.etSpyj, Html.fromHtml(item.spyj))

                val canEdit = item.isCurrentNode
                val etSpyj = helper.getView<EditText>(R.id.etSpyj)
                etSpyj.isEnabled = canEdit

                // 手写签
                val tvSign = helper.tvSign
                GradientDrawable().apply {
                    setStroke(ConvertUtils.dp2px(1f), ContextCompat.getColor(mContext, R.color.colorPrimary))
                    setColor(Color.WHITE)
                }.let { tvSign.background = it }
                tvSign.visibility = if (canEdit) View.VISIBLE else View.GONE
                tvSign.safeClicks().subscribe { _ -> etSpyj.setText("${etSpyj.text} ${Global.loginInfo!!.userName} ${DateTime().toString("yyyy年MM月dd日")}") }
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val helper = super.onCreateDefViewHolder(parent, viewType)
        if (viewType == TYPE_SPYJ) {
            helper.etSpyj.textChanges2().filter { it.isNotEmpty() }.subscribe {
                val adapterPos = helper.adapterPosition
                if (adapterPos == -1) return@subscribe
                val index = adapterPos - 1
                val target = mData[index]
                if (target is Spyj) {
                    target.spyj = it
                }
                Logger.e(index.toString())
            }
        }
        return helper
    }


}