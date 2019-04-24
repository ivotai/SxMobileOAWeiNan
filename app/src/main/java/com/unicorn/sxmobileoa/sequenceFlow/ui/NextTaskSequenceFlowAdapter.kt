package com.unicorn.sxmobileoa.sequenceFlow.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow

class NextTaskSequenceFlowAdapter : BaseQuickAdapter<NextTaskSequenceFlow, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: NextTaskSequenceFlow) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.nextTaskShowName

        // 选中效果
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

        // 单选
        tvText.safeClicks().subscribe { _ ->
            RxBus.get().post(item)
            if (item.isSelected) return@subscribe

            data.forEach { it.isSelected = it == item }
            notifyDataSetChanged()
        }
    }

}

